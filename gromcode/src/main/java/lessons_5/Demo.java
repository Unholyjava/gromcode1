package lessons_5;

import org.hibernate.Session;

public class Demo {

	public static void main(String[] args) {
		Session session = new HibernateUtils().createSessionFactory().openSession();
		session.getTransaction().begin();
		
		Product product = new Product();
		product.setId(10);
		product.setName("cup");
		product.setDescription("white");
		product.setPrice(50);
		
		session.save(product);
		session.getTransaction().commit();
		
		System.out.println("Congratulations!");
		session.close();
	}

}
