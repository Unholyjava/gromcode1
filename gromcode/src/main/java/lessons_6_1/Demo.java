package lessons_6_1;

import java.util.Arrays;
import java.util.List;

public class Demo {

	public static void main(String[] args) {
		Product product = new Product();
		product.setName("new cup1");
		product.setDescription("black");
		product.setPrice(60);
		
		ProductDAO productDao = new ProductDAO();
		productDao.save(product);
		productDao.save(product);
		productDao.save(product);
		productDao.delete(product);
		productDao.save(product);
		product.setPrice(65);
		productDao.update(product);
		
		Product product1 = new Product();
		product1.setName("new table1");
		product1.setDescription("black");
		product1.setPrice(120);
		
		Product product2 = new Product();
		product2.setName("new table2");
		product2.setDescription("black");
		product2.setPrice(120);
		
		Product product3 = new Product();
		product3.setName("new table3");
		product3.setDescription("black");
		product3.setPrice(120);
		
		List <Product> productList = Arrays.asList(product1, product2, product3);
		productDao.saveAll(productList);
		
		product1.setPrice(150);
		product2.setPrice(150);
		product3.setPrice(150);
		productDao.updateAll(productList);
		
		productDao.deleteAll(productList);
		
		
		System.out.println("Congratulations!");
	}

}
