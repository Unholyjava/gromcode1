package lessons_6_1;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ProductDAO {

	private SessionFactory sessionFactory; 
	
	public Product save(Product product) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = createSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();
			
			session.save(product);
			
			transaction.commit();
		} catch (HibernateException e) {
			System.err.println("Save is failed");
			System.err.println(e.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			if (session != null){
				session.close();
			}
		}
		System.out.println("Save is done");
		return product;
	}
	
	public Product update(Product product) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = createSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();
			
			session.update(product);
			
			transaction.commit();
		} catch (HibernateException e) {
			System.err.println("Update is failed");
			System.err.println(e.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			if (session != null){
				session.close();
			}
		}
		System.out.println("Update is done");
		return product;
	}
	
	public Product delete(Product product) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = createSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();
			
			session.delete(product);
			
			transaction.commit();
		} catch (HibernateException e) {
			System.err.println("Delete is failed");
			System.err.println(e.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			if (session != null){
				session.close();
			}
		}
		System.out.println("Delete is done");
		return product;
	}
	
	public void saveAll(List <Product> products) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = createSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();
			
			for (Product product : products) {
				session.save(product);
			}
			
			transaction.commit();
		} catch (HibernateException e) {
			System.err.println("Save List is failed");
			System.err.println(e.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			if (session != null){
				session.close();
			}
		}
		System.out.println("Save List is done");
	}
	
	public void updateAll(List <Product> products) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = createSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();
			
			for (Product product : products) {
				session.update(product);
			}
			
			transaction.commit();
		} catch (HibernateException e) {
			System.err.println("Update List is failed");
			System.err.println(e.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			if (session != null){
				session.close();
			}
		}
		System.out.println("Update List is done");
	}
	
	public void deleteAll(List <Product> products) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = createSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();
			
			for (Product product : products) {
				session.delete(product);
			}
			
			transaction.commit();
		} catch (HibernateException e) {
			System.err.println("Delete List is failed");
			System.err.println(e.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			if (session != null){
				session.close();
			}
		}
		System.out.println("Delete List is done");
	}
	
	public SessionFactory createSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
		return sessionFactory;
	}

}
