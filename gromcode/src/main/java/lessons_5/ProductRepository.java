package lessons_5;

import org.hibernate.Session;

public class ProductRepository {
	
	public ProductRepository(Session session) {
		this.session = session;
	}

	private Session session;
	

	public void save(Product product) {
		session.getTransaction().begin();
		session.save(product);
		session.getTransaction().commit();
	}
	
	public void delete(long id) {
		session.getTransaction().begin();
		session.delete(session.get(Product.class, id));
		session.getTransaction().commit();
	}
	
	public void update(Product product) {
		session.getTransaction().begin();
		session.update(product);
		session.getTransaction().commit();
	}
	
	/**
	 * @return the session
	 */
	public Session getSession() {
		return session;
	}

}
