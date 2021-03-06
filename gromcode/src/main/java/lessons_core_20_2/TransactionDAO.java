package lessons_core_20_2;

import java.util.Calendar;
import java.util.Date;

import lessons_core_20_2.exception.BadRequestException;
import lessons_core_20_2.exception.InternalServerException;
import lessons_core_20_2.exception.LimitExceeded;

public class TransactionDAO {
	private final int maxStorageLenght = 10;
	private Transaction[] transactions = new Transaction[maxStorageLenght];
	private Utils utils = new Utils();
	
	public Transaction save(Transaction transaction) throws InternalServerException, BadRequestException {
		validate(transaction);
		int index = 0;
		for (Transaction tr : transactions) {
			if (tr == null) {
				transactions[index] = transaction;
				return transactions[index];
			}
			index++;
		}
		return null;
	}

	private void validate(Transaction transaction) throws InternalServerException, BadRequestException {
		if (transaction.getAmount() > utils.getLimitSimpleTransactionAmount()) {
			throw new LimitExceeded("Transaction limit exceed " + transaction.getId()
				+ ". Can't be saved");
		}
		
		int sum = transaction.getAmount();
		int count = 1;
		for (Transaction tr : getTransactionsPerDay(transaction.getDateCreated())) {
			sum += tr.getAmount();
			count++;
		}
		if (sum > utils.getLimitTransactionsPerDayAmount()) {
			throw new LimitExceeded("Transaction limit per day amount exceed " + transaction.getId()
			+ ". Can't be saved");
		}
		
		if (count > utils.getLimitTransactionsPerDayCount()) {
			throw new LimitExceeded("Transaction limit per day count exceed " + transaction.getId()
			+ ". Can't be saved");
		}
		
		int indexOfCityArray = 0;
		for (String city : utils.getCities()) {
			if (city.equals(transaction.getCity())) {
				break;
			}
			indexOfCityArray++;
		}
		if (indexOfCityArray == utils.getCities().length) {
			throw new BadRequestException("Transaction's city isn't allowed " + transaction.getId()
			+ ". Can't be saved");
		}
		
		int numberNotNull = 0;
		for (Transaction tr : transactions) {
			if (tr != null) {
				numberNotNull++;
			}			
		}
		if (numberNotNull == maxStorageLenght) {
			throw new InternalServerException("Transaction's storage is full, transaction " + transaction.getId()
			+ ". Can't be saved");
		}
		
		for (Transaction tr : transactions) {
			if (tr != null && transaction.getId() == tr.getId()) {
				throw new BadRequestException("Transaction's id is used in storage " + transaction.getId()
				+ ". Can't be saved");
			}
		}
	}
	
	public Transaction[] transactionList() {
		int maxListAll = 0;
		for (Transaction tr : transactions) {
			if (tr != null) {
				maxListAll++;
			}
		}
		Transaction[] transactionsAll = new Transaction[maxListAll];
		int index = 0;
		for (Transaction tr : transactions) {
			if (tr != null) {
				transactionsAll[index] = tr;
			}
			index++;
		}
		return transactionsAll;
	}
	
	public Transaction[] transactionList(String city) {
		int maxListCity = 0;
		for (Transaction tr : transactions) {
			if (tr != null && city.equals(tr.getCity())) {
				maxListCity++;
			}
		}
		Transaction[] transactionsOfCity = new Transaction[maxListCity];
		int index = 0;
		for (Transaction tr : transactions) {
			if (tr != null && city.equals(tr.getCity())) {
				transactionsOfCity[index] = tr;
				index++;
			}
		}
		return transactionsOfCity;
	}
	
	public Transaction[] transactionList(int amount) {
		int maxListAmount = 0;
		for (Transaction tr : transactions) {
			if (tr != null && amount == tr.getAmount()) {
				maxListAmount++;
			}
		}
		Transaction[] transactionsOfAmount = new Transaction[maxListAmount];
		int index = 0;
		for (Transaction tr : transactions) {
			if (tr != null && amount == tr.getAmount()) {
				transactionsOfAmount[index] = tr;
				index++;
			}
		}
		return transactionsOfAmount;
	}
	
	private Transaction[] getTransactionsPerDay(Date dateOfCurTransaction) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateOfCurTransaction);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		
		int count = 0;
		for (Transaction transaction : transactions) {
			if (transaction != null) {
				calendar.setTime(transaction.getDateCreated());
				int trMonth = calendar.get(Calendar.MONTH);
				int trDay = calendar.get(Calendar.DAY_OF_MONTH);
				if (trMonth == month && trDay == day) {
					count++;
				}
			}
		}
		
		Transaction[] result = new Transaction[count];
		int index = 0;
		for (Transaction transaction : transactions) {
			if (transaction != null) {
				calendar.setTime(transaction.getDateCreated());
				int trMonth = calendar.get(Calendar.MONTH);
				int trDay = calendar.get(Calendar.DAY_OF_MONTH);
				if (trMonth == month && trDay == day) {
					result[index] = transaction;
					index++;
				}
			}
		}
		return result;
	}
}
