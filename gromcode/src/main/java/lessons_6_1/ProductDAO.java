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
		Transaction transaction = null;
		try (Session session = createSessionFactory().openSession()) {
			transaction = session.getTransaction();
			transaction.begin();
			
			session.save(product);
			
			transaction.commit();
			System.out.println("Save is done");
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("Save is failed");
			System.err.println(e.getMessage());
		}
		return product;
	}
	
	public Product update(Product product) {
		Transaction transaction = null;
		try (Session session = createSessionFactory().openSession()) {
			transaction = session.getTransaction();
			transaction.begin();
			
			session.update(product);
			
			transaction.commit();
			System.out.println("Update is done");
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("Update is failed");
			System.err.println(e.getMessage());
		} 
		return product;
	}
	
	public Product delete(Product product) {
		Transaction transaction = null;
		try (Session session = createSessionFactory().openSession()) {
			transaction = session.getTransaction();
			transaction.begin();
			
			session.delete(product);
			
			transaction.commit();
			System.out.println("Delete is done");
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("Delete is failed");
			System.err.println(e.getMessage());
		} 
		return product;
	}
	
	public void saveAll(List <Product> products) {
		Transaction transaction = null;
		try (Session session = createSessionFactory().openSession()) {
			transaction = session.getTransaction();
			transaction.begin();
			
			for (Product product : products) {
				session.save(product);
			}
			
			transaction.commit();
			System.out.println("Save List is done");
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("Save List is failed");
			System.err.println(e.getMessage());
		} 
	}
	
	public void updateAll(List <Product> products) {
		Transaction transaction = null;
		try (Session session = createSessionFactory().openSession()) {
			transaction = session.getTransaction();
			transaction.begin();
			
			for (Product product : products) {
				session.update(product);
			}
			
			transaction.commit();
			System.out.println("Update List is done");
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("Update List is failed");
			System.err.println(e.getMessage());
		} 
	}
	
	public void deleteAll(List <Product> products) {
		Transaction transaction = null;
		try (Session session = createSessionFactory().openSession()) {
			transaction = session.getTransaction();
			transaction.begin();
			
			for (Product product : products) {
				session.delete(product);
			}
			
			transaction.commit();
			System.out.println("Delete List is done");
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("Delete List is failed");
			System.err.println(e.getMessage());
		} 
	}
	
	public SessionFactory createSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
		return sessionFactory;
	}

}
