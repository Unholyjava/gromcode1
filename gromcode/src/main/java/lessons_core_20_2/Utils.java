package lessons_core_20_2;

public class Utils {
	private int limitTransactionsPerDayCount = 10;
	private int limitTransactionsPerDayAmount = 100;
	private int limitSimpleTransactionAmount = 40;
	private String[] cities = {"Kiev", "Odessa"};
	
	public int getLimitTransactionsPerDayCount() {
		return limitTransactionsPerDayCount;
	}
	
	public int getLimitTransactionsPerDayAmount() {
		return limitTransactionsPerDayAmount;
	}
	
	public int getLimitSimpleTransactionAmount() {
		return limitSimpleTransactionAmount;
	}
	
	public String[] getCities() {
		return cities;
	}
	
	
}
