package lessons_6_3;

public class Demo {

	public static void main(String[] args) {
		ProductDAO productDao = new ProductDAO();
		
//		Product product1 = new Product();
//		product1.setName("a_new cup1");
//		product1.setDescription("black");
//		product1.setPrice(60);
//		
//		Product product2 = new Product();
//		product2.setName("b_new cup2");
//		product2.setDescription("black");
//		product2.setPrice(50);
//		
//		Product product3 = new Product();
//		product3.setName("d_new cup3");
//		product3.setDescription("black");
//		product3.setPrice(80);
//		
//		Product product4 = new Product();
//		product4.setName("c_new cup4");
//		product4.setDescription("black");
//		product4.setPrice(90);
//		
//		productDao.save(product1);
//		productDao.save(product2);
//		productDao.save(product3);
//		productDao.save(product4);
		
		System.out.println(productDao.findById(1).toString());
		System.out.println(productDao.findByName("b_new cup2"));
		System.out.println(productDao.findByName("new cup").toString());
		System.out.println(productDao.findByContainedName("new").toString());
		System.out.println(productDao.findByNameSortedAsc("new cup").toString());
		System.out.println(productDao.findByNameSortedDesc("new cup").toString());
		System.out.println(productDao.findByPrice(71, 20).toString());
		System.out.println(productDao.findByPriceSortedDesc(71, 20).toString());
						
		System.out.println("Congratulations!");
	}

}
