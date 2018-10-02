package lessons_8;

import javax.persistence.NoResultException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class CommonDAO<T> implements DAO<T>{
	private SessionFactory sessionFactory; 
	private static final String SELECT_HOTEL_BY_ID = "SELECT * FROM HOTELS WHERE ID = :id";
	private static final String SELECT_ORDER_BY_ID = "SELECT * FROM ORDERS WHERE ID = :id";
	private static final String SELECT_ROOM_BY_ID = "SELECT * FROM ROOMS WHERE ID = :id";
	private static final String SELECT_USER_BY_ID = "SELECT * FROM USERS WHERE ID = :id";
	private final Class<T> classCurrent;
	
	public CommonDAO (Class<T> classCurrent) {
		this.classCurrent = classCurrent;
	}
	
	public T save(T entity) {
		String entityClass = entity.getClass().getSimpleName();
		Transaction transaction = null;
		try (Session session = createSessionFactory().openSession()) {
			transaction = session.getTransaction();
			transaction.begin();
			
			session.save(entity);
			
			transaction.commit();
			System.out.println("Save " + entityClass + " is done");
		} catch (HibernateException e) {
			System.err.println("Save " + entityClass + " is failed");
			System.err.println(e.getMessage());
			throw e;
		}
		return entity;
	}
	
	
	public T delete(long id) {
		Transaction transaction = null;
		T entity = findById(id);
		if (entity == null) {
			System.out.println("Not found by ID");
			return null;
		}
		String entityClass = entity.getClass().getSimpleName();
		try (Session session = createSessionFactory().openSession()) {
			transaction = session.getTransaction();
			transaction.begin();
			
			session.delete(entity);
			
			transaction.commit();
			System.out.println("Delete " + entityClass + " is done");
		} catch (HibernateException e) {
			System.err.println("Delete " + entityClass + " is failed");
			System.err.println(e.getMessage());
			throw e;
		} 
		return entity;
	}
	
	public T update(T entity) {
		String entityClass = entity.getClass().getSimpleName();
		Transaction transaction = null;
		try (Session session = createSessionFactory().openSession()) {
			transaction = session.getTransaction();
			transaction.begin();
			
			session.update(entity);
			
			transaction.commit();
			System.out.println("Update " + entityClass + " is done");
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("Update " + entityClass + " is failed");
			System.err.println(e.getMessage());
			throw e;
		} 
		return entity;
	}
	
	public T findById(long id) {
		String entityClass = classCurrent.getSimpleName();
		try (Session session = createSessionFactory().openSession()) {
			Query<T> query;
			switch (entityClass) {
				case "Hotel":
					query = session.createNativeQuery(SELECT_HOTEL_BY_ID, classCurrent);
					break;
				case "Room":
					query = session.createNativeQuery(SELECT_ROOM_BY_ID, classCurrent);
					break;
				case "User":
					query = session.createNativeQuery(SELECT_USER_BY_ID, classCurrent);
					break;
				case "Order":
					query = session.createNativeQuery(SELECT_ORDER_BY_ID, classCurrent);
					break;
				default:
					throw new Exception("bad name of class");
				}
					
			query.setParameter("id", id);
			return query.getSingleResult();
		} catch (HibernateException e) {
			System.err.println("findById of " + entityClass + " is failed");
			System.err.println(e.getMessage());
			throw e;
		} catch (NoResultException e) {
			System.out.println("Not found " + entityClass + " by ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public SessionFactory createSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
		return sessionFactory;
	}
}
