package lessons_4;

import java.util.ArrayList;
import java.util.List;

public class Demo {

	public static void main(String[] args) {
		Product product1 = new Product(55, "aaa", "aaaa", 20);
		Product product2 = new Product(66, "aaa", "aaaa", 20);
		Product product3 = new Product(44, "aaa", "aaaa", 20);
		List<Product> products = new ArrayList<>();
		products.add(product1);
		products.add(product2);
		products.add(product3);
		TransactionDemo transactionDemo = new TransactionDemo();
		transactionDemo.save(products);
	}
}
