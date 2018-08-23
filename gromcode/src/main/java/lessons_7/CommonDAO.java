package lessons_7;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public abstract class CommonDAO <T> {
	private SessionFactory sessionFactory; 
	
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
	
	public abstract T findById(long id); 
	
	public SessionFactory createSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
		return sessionFactory;
	}
}
