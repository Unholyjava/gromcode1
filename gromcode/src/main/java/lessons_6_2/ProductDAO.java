package lessons_6_2;

import java.util.List;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class ProductDAO {

	private static final String FROM_PRODUCT_BY_PRICE_DELTA_SORT_DESC = "FROM Product as product WHERE product.price >= :min AND product.price <= :max ORDER BY product.price DESC";
	private static final String FROM_PRODUCT_BY_LIKE_NAME_SORT_DESC = "FROM Product as product WHERE product.name LIKE :name ORDER BY product.name DESC";
	private static final String FROM_PRODUCT_BY_LIKE_NAME_SORT_ASC = "FROM Product as product WHERE product.name LIKE :name ORDER BY product.name ASC";
	private static final String FROM_PRODUCT_BY_PRICE_DELTA = "FROM Product as product WHERE product.price >= :min AND product.price <= :max";
	private static final String FROM_PRODUCT_BY_LIKE_NAME = "FROM Product as product WHERE product.name LIKE :name";
	private static final String FROM_PRODUCT_BY_NAME = "FROM Product as product WHERE product.name = :name";
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
	
	public Product findById(long id) {
		try (Session session = createSessionFactory().openSession()) {
			return session.get(Product.class, id);
		} catch (HibernateException e) {
			System.err.println("findById is failed");
			System.err.println(e.getMessage());
		} 
		return null;
	}
	
	public List<Product> findByName(String name) {
		try (Session session = createSessionFactory().openSession()) {
			Query<Product> query = session.createQuery(FROM_PRODUCT_BY_NAME);
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
			Query<Product> query = session.createQuery(FROM_PRODUCT_BY_LIKE_NAME);
			return createListByLikeName(name, query);
		} catch (HibernateException e) {
			System.err.println("findByContainedName is failed");
			System.err.println(e.getMessage());
		} 
		return null;
	}
	
	
	public List<Product> findByNameSortedAsc(String name) {
		try (Session session = createSessionFactory().openSession()) {
			Query<Product> query = session.createQuery(FROM_PRODUCT_BY_LIKE_NAME_SORT_ASC);
			return createListByLikeName(name, query);
		} catch (HibernateException e) {
			System.err.println("findByNameSortedAsc is failed");
			System.err.println(e.getMessage());
		} 
		return null;
	}

	public List<Product> findByNameSortedDesc(String name) {
		try (Session session = createSessionFactory().openSession()) {
			Query<Product> query = session.createQuery(FROM_PRODUCT_BY_LIKE_NAME_SORT_DESC);
			return createListByLikeName(name, query);
		} catch (HibernateException e) {
			System.err.println("findByNameSortedDesc is failed");
			System.err.println(e.getMessage());
		} 
		return null;
	}
	
	private List<Product> createListByLikeName(String name, Query<Product> query) {
		query.setParameter("name", "%" + name + "%");
		return query.list();
	}
	
	public List<Product> findByPrice(int price, int delta) {
		try (Session session = createSessionFactory().openSession()) {
			Query<Product> query = session.createQuery(FROM_PRODUCT_BY_PRICE_DELTA);
			return createListByPriceDelta(price, delta, query);
		} catch (HibernateException e) {
			System.err.println("findByPrice is failed");
			System.err.println(e.getMessage());
		} 
		return null;
	}

	public List<Product> findByPriceSortedDesc(int price, int delta) {
		try (Session session = createSessionFactory().openSession()) {
			Query<Product> query = session.createQuery(FROM_PRODUCT_BY_PRICE_DELTA_SORT_DESC);
			return createListByPriceDelta(price, delta, query);
		} catch (HibernateException e) {
			System.err.println("findByPrice is failed");
			System.err.println(e.getMessage());
		} 
		return null;
	}
	
	private List<Product> createListByPriceDelta(int price, int delta, Query<Product> query) {
		query.setParameter("min", price - delta);
		query.setParameter("max", price + delta);
		return query.list();
	}
	
	public SessionFactory createSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
		return sessionFactory;
	}

}
