package lessons_6_2;

import java.util.List;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

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
	
	public Product findById(long id) {
		Session session = null;
		try {
			session = createSessionFactory().openSession();
			return session.get(Product.class, id);
		} catch (HibernateException e) {
			System.err.println("findById is failed");
			System.err.println(e.getMessage());
		} finally {
			if (session != null){
				session.close();
			}
		}
		return null;
	}
	
	public List<Product> findByName(String name) {
		Session session = null;
		try {
			session = createSessionFactory().openSession();
			Query<Product> query = session.createQuery("FROM Product as product WHERE product.name = :name");
			query.setParameter("name", name);
			return query.list();
		} catch (HibernateException e) {
			System.err.println("findByName is failed");
			System.err.println(e.getMessage());
		} finally {
			if (session != null){
				session.close();
			}
		}
		return null;
	}
	
	public List<Product> findByContainedName(String name) {
		Session session = null;
		try {
			session = createSessionFactory().openSession();
			Query<Product> query = session.createQuery("FROM Product as product WHERE product.name LIKE :name");
			query.setParameter("name", "%" + name + "%");
			return query.list();
		} catch (HibernateException e) {
			System.err.println("findByContainedName is failed");
			System.err.println(e.getMessage());
		} finally {
			if (session != null){
				session.close();
			}
		}
		return null;
	}
	
	public List<Product> findByPrice(int price, int delta) {
		Session session = null;
		try {
			session = createSessionFactory().openSession();
			Query<Product> query = session.createQuery("FROM Product as product WHERE product.price >= :min AND product.price <= :max");
			query.setParameter("min", price - delta);
			query.setParameter("max", price + delta);
			return query.list();
		} catch (HibernateException e) {
			System.err.println("findByPrice is failed");
			System.err.println(e.getMessage());
		} finally {
			if (session != null){
				session.close();
			}
		}
		return null;
	}
	
	public List<Product> findByNameSortedAsc(String name) {
		Session session = null;
		try {
			session = createSessionFactory().openSession();
			Query<Product> query = session.createQuery("FROM Product as product WHERE product.name LIKE :name ORDER BY product.name ASC");
			query.setParameter("name", "%" + name + "%");
			return query.list();
		} catch (HibernateException e) {
			System.err.println("findByNameSortedAsc is failed");
			System.err.println(e.getMessage());
		} finally {
			if (session != null){
				session.close();
			}
		}
		return null;
	}
	
	public List<Product> findByNameSortedDesc(String name) {
		Session session = null;
		try {
			session = createSessionFactory().openSession();
			Query<Product> query = session.createQuery("FROM Product as product WHERE product.name LIKE :name ORDER BY product.name DESC");
			query.setParameter("name", "%" + name + "%");
			return query.list();
		} catch (HibernateException e) {
			System.err.println("findByNameSortedDesc is failed");
			System.err.println(e.getMessage());
		} finally {
			if (session != null){
				session.close();
			}
		}
		return null;
	}
	
	public List<Product> findByPriceSortedDesc(int price, int delta) {
		Session session = null;
		try {
			session = createSessionFactory().openSession();
			Query<Product> query = session.createQuery("FROM Product as product WHERE product.price >= :min AND product.price <= :max ORDER BY product.price DESC");
			query.setParameter("min", price - delta);
			query.setParameter("max", price + delta);
			return query.list();
		} catch (HibernateException e) {
			System.err.println("findByPrice is failed");
			System.err.println(e.getMessage());
		} finally {
			if (session != null){
				session.close();
			}
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
