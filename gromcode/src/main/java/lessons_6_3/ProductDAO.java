package lessons_6_3;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class ProductDAO {

	private static final String SELECT_PRODUCT_BY_PRICE_DELTA_SORT_DESC = "SELECT * FROM PRODUCT WHERE PRICE >= :min AND PRICE <= :max ORDER BY PRICE DESC";
	private static final String SELECT_BY_PRODUCT_BY_LIKE_NAME_SORT_DESC = "SELECT * FROM PRODUCT WHERE NAME LIKE :name ORDER BY NAME DESC";
	private static final String SELECT_PRODUCT_BY_LIKE_NAME_SORT_ASC = "SELECT * FROM PRODUCT WHERE NAME LIKE :name ORDER BY NAME ASC";
	private static final String SELECT_PRODUCT_BY_PRICE_DELTA = "SELECT * FROM PRODUCT WHERE PRICE >= :min AND PRICE <= :max";
	private static final String SELECT_PRODUCT_BY_LIKE_NAME = "SELECT * FROM PRODUCT WHERE NAME LIKE :name";
	private static final String SELECT_PRODUCT_BY_NAME = "SELECT * FROM PRODUCT WHERE NAME = :name";
	private static final String SELECT_PRODUCT_BY_ID = "SELECT * FROM PRODUCT WHERE ID = :id";
	private SessionFactory sessionFactory; 
	
	public Product save(Product product) {
		Transaction transaction = null;
		try (Session session = createSessionFactory().openSession()) {
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
		} 
		System.out.println("Save is done");
		return product;
	}
	
	public Product findById(long id) {
		try (Session session = createSessionFactory().openSession()) {
			Query<Product> query = session.createNativeQuery(SELECT_PRODUCT_BY_ID, Product.class);
			query.setParameter("id", id);
			return query.getSingleResult();
		} catch (HibernateException e) {
			System.err.println("findById is failed");
			System.err.println(e.getMessage());
		} 
		return null;
	}
	
	public List<Product> findByName(String name) {
		try (Session session = createSessionFactory().openSession()) {
			Query<Product> query = session.createNativeQuery(SELECT_PRODUCT_BY_NAME, Product.class);
			query.setParameter("name", name);
			return query.list();
		} catch (HibernateException e) {
			System.err.println("findByName is failed");
			System.err.println(e.getMessage());
		} 
		return null;
	}
	
	public List<Product> findByContainedName(String name) {
		try (Session session = createSessionFactory().openSession()) {
			Query<Product> query = session.createNativeQuery(SELECT_PRODUCT_BY_LIKE_NAME, Product.class);
			query.setParameter("name", "%" + name + "%");
			return query.list();
		} catch (HibernateException e) {
			System.err.println("findByContainedName is failed");
			System.err.println(e.getMessage());
		} 
		return null;
	}
	
	public List<Product> findByPrice(int price, int delta) {
		try (Session session = createSessionFactory().openSession()) {
			Query<Product> query = session.createNativeQuery(SELECT_PRODUCT_BY_PRICE_DELTA, Product.class);
			query.setParameter("min", price - delta);
			query.setParameter("max", price + delta);
			return query.list();
		} catch (HibernateException e) {
			System.err.println("findByPrice is failed");
			System.err.println(e.getMessage());
		} 
		return null;
	}
	
	public List<Product> findByNameSortedAsc(String name) {
		try (Session session = createSessionFactory().openSession()) {
			Query<Product> query = session.createNativeQuery(SELECT_PRODUCT_BY_LIKE_NAME_SORT_ASC, Product.class);
			query.setParameter("name", "%" + name + "%");
			return query.list();
		} catch (HibernateException e) {
			System.err.println("findByNameSortedAsc is failed");
			System.err.println(e.getMessage());
		} 
		return null;
	}
	
	public List<Product> findByNameSortedDesc(String name) {
		try (Session session = createSessionFactory().openSession()) {
			Query<Product> query = session.createNativeQuery(SELECT_BY_PRODUCT_BY_LIKE_NAME_SORT_DESC, Product.class);
			query.setParameter("name", "%" + name + "%");
			return query.list();
		} catch (HibernateException e) {
			System.err.println("findByNameSortedDesc is failed");
			System.err.println(e.getMessage());
		} 
		return null;
	}
	
	public List<Product> findByPriceSortedDesc(int price, int delta) {
		try (Session session = createSessionFactory().openSession()) {
			Query<Product> query = session.createNativeQuery(SELECT_PRODUCT_BY_PRICE_DELTA_SORT_DESC, Product.class);
			query.setParameter("min", price - delta);
			query.setParameter("max", price + delta);
			return query.list();
		} catch (HibernateException e) {
			System.err.println("findByPriceSortedDesc is failed");
			System.err.println(e.getMessage());
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
