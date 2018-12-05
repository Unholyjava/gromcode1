package lessons_core_20_2;

import lessons_core_20_2.exception.BadRequestException;
import lessons_core_20_2.exception.InternalServerException;

public class Controller {
	private TransactionDAO transactionDAO = new TransactionDAO();
	
	public Transaction save(Transaction transaction) throws InternalServerException, BadRequestException {
		return transactionDAO.save(transaction);
	}
	
	Transaction[] transactionList() {
		
		return null;
	}
	
	Transaction[] transactionList(String city) {
		
		return null;
	}
	
	Transaction[] transactionList(int amount) {
	
		return null;
	}
}
