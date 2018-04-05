package lessons_4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TransactionDemo {

	private static final String DB_URL = "jdbc:oracle:thin:@gromcodegregorydb-lessons.cykue0lynxa0.us-east-2.rds.amazonaws.com:1521:ORCL";

	private static final String USER = "main";
	private static final String PASS = "shmopka1488";
	private static final String ERROR = "Transaction error";

	public void save(List<Product> products) {
		try (Connection connection = getConnection()){
			saveList(products, connection);
		} catch (SQLException e) {
			System.out.println(ERROR);
			e.printStackTrace();
		}
	}

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL, USER, PASS);
	}
	
	private void saveList(List<Product> products, Connection connection) throws SQLException {
		long idCurrent = -1;
		try (PreparedStatement prepareStatement = connection
				.prepareStatement("INSERT INTO PRODUCT VALUES (?, ?, ?, ?)")) {
			connection.setAutoCommit(false);
			for (Product product : products) {
				prepareStatement.setLong(1, product.getId());
				prepareStatement.setString(2, product.getName());
				prepareStatement.setString(3, product.getDescription());
				prepareStatement.setInt(4, product.getPrice());
				idCurrent = product.getId(); 
				int response = prepareStatement.executeUpdate();
				System.out.println("save was finished with result " + response);
			}
			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
			System.out.println("product not save: id = " + idCurrent);
			throw e;
		}
	}
}
