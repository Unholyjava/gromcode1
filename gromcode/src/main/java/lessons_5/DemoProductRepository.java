package lessons_5;

import org.hibernate.Session;

public class DemoProductRepository {

	public static void main(String[] args) {
		Session session = new HibernateUtils().createSessionFactory().openSession();
		ProductRepository productRepository = new ProductRepository(session);
		
//		Product product = new Product();
//		product.setId(11);
//		product.setName("cup");
//		product.setDescription("black");
//		product.setPrice(70);
//		productRepository.save(product);
		
//		product.setPrice(110);
//		productRepository.update(product);
		
		productRepository.delete(11);
		
		System.out.println("Congratulations!");
		session.close();
	
	}

}
