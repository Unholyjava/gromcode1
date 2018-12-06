package lessons_core_20_2;

import java.util.Date;

import lessons_core_20_2.exception.BadRequestException;
import lessons_core_20_2.exception.InternalServerException;

public class Demo {

	public static void main(String[] args) throws InternalServerException, BadRequestException {
		System.out.println("start");
		Transaction trans1 = new Transaction(1, "Kiev", 20, "for product1", TransactionType.INCOME, new Date());
		Transaction trans2 = new Transaction(2, "Kiev", 30, "for product2", TransactionType.INCOME, new Date());
		Transaction trans3 = new Transaction(3, "Kiev", 10, "for product3", TransactionType.INCOME, new Date());
		Transaction trans4 = new Transaction(4, "Kiev", 3, "for product4", TransactionType.INCOME, new Date());
		Transaction trans5 = new Transaction(5, "Odessa", 5, "for product5", TransactionType.INCOME, new Date());
		Transaction trans6 = new Transaction(6, "Odessa", 2, "for product6", TransactionType.OUTCOME, new Date());
		Transaction trans7 = new Transaction(7, "Odessa", 5, "for product7", TransactionType.OUTCOME, new Date());
		Transaction trans8 = new Transaction(8, "Kiev", 1, "for product8", TransactionType.INCOME, new Date());
		Transaction trans9 = new Transaction(9, "Kiev", 1, "for product9", TransactionType.INCOME, new Date());
		
		Transaction trans10 = new Transaction(10, "Dnepr", 1, "for product10", TransactionType.INCOME, new Date());
		Transaction trans11 = new Transaction(11, "Kiev", 45, "for product11", TransactionType.INCOME, new Date());
		Transaction trans12 = new Transaction(12, "Kiev", 35, "for product12", TransactionType.INCOME, new Date());
		Transaction trans13 = new Transaction(1, "Kiev", 3, "for product13", TransactionType.INCOME, new Date());
		
		Transaction trans14 = new Transaction(14, "Kiev", 5, "for product14", TransactionType.INCOME, new Date());
		Transaction trans15 = new Transaction(15, "Kiev", 5, "for product15", TransactionType.INCOME, new Date());
		Controller controller = new Controller();
		controller.save(trans1);
		controller.save(trans2);
		controller.save(trans3);
		controller.save(trans4);
		controller.save(trans5);
		controller.save(trans6);
		controller.save(trans7);
		controller.save(trans8);
		controller.save(trans9);
		
		//no save, "Transaction's city isn't allowed .."
		try {
			controller.save(trans10);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		//no save, "Transaction limit exceed .."
		try {
			controller.save(trans11);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		//no save, "Transaction limit per day amount exceed .."
		try {
			controller.save(trans12);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		//no save, "Transaction's id is used in storage .."
		try {
			controller.save(trans13);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		controller.save(trans14);
		
		//no save, "Transaction's storage is full, transaction .."
		try {
			controller.save(trans15);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		for (Transaction tr : controller.transactionList()) {
			System.out.println(tr);
		}
		System.out.println("\n");
		
		for (Transaction tr : controller.transactionList(5)) {
			System.out.println(tr);
		}
		System.out.println("\n");
		
		for (Transaction tr : controller.transactionList("Odessa")) {
			System.out.println(tr);
		}
		
	}

}
