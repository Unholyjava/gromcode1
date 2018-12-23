package lessons_8;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class CommonDAO<T> implements DAO<T>{
	private SessionFactory sessionFactory; 
	private final Class<T> classCurrent;
	
	public CommonDAO (Class<T> classCurrent) {
		this.classCurrent = classCurrent;
	}
	
	public T save(T entity) throws Exception {
		String entityClass = entity.getClass().getSimpleName();
		Transaction transaction = null;
		try (Session session = createSessionFactory().openSession()) {
			transaction = session.getTransaction();
			transaction.begin();
			
			session.save(entity);
			
			transaction.commit();
			System.out.println("Save " + entityClass + " is done");
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new Exception ("Save " + entityClass + " is failed");
		}
		return entity;
	}
	
	
	public T delete(long id) throws Exception {
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
			e.printStackTrace();
			throw new Exception ("Delete " + entityClass + " is failed");
		} 
		return entity;
	}
	
	public T update(T entity) throws Exception {
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
			e.printStackTrace();
			throw new Exception("Update " + entityClass + " is failed");
		} 
		return entity;
	}
	
	public T findById(long id) throws Exception {
		String entityClass = classCurrent.getSimpleName();
		try (Session session = createSessionFactory().openSession()) {
			return session.get(classCurrent, id);
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new Exception ("findById of " + entityClass + " is failed");
		}
	}
	
	public SessionFactory createSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
		return sessionFactory;
	}
}
