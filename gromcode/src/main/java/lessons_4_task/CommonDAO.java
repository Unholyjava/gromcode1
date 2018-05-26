package lessons_4_task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommonDAO {

	protected static final String DB_URL = "jdbc:oracle:thin:@gromcodegregorydb-lessons.cykue0lynxa0.us-east-2.rds.amazonaws.com:1521:ORCL";
	protected static final String USER = "main";
	protected static final String PASS = "shmopka1488";
	protected static final String ERROR = "Something went wrong";
	private static Connection connection;
	
	public void updateIdStorage(File file, Storage storage) throws SQLException {
		try (Connection connection = getConnection();
				PreparedStatement prepareStatement = connection
						.prepareStatement("UPDATE FILES SET ID_STORAGE = ? WHERE ID = ?")) {
			prepareStatement.setLong(2, file.getId());
			prepareStatement.setLong(1, storage.getId());
			int response = prepareStatement.executeUpdate();
			System.out.println("update IdStorage was finished with result " + response);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void updateIdStorageFile(File file, Storage storage) throws SQLException {
		try {
			updateIdStorage(file, storage);
			getConnection().commit();
		} catch (SQLException errorTransaction) {
			getConnection().rollback();
			errorTransaction.printStackTrace();
			throw errorTransaction;
		}
	}
	
	public void updateIdStorageFileArray(File[] fileArray, Storage storage) throws SQLException {
		try (Connection connection = getConnection();
				PreparedStatement prepareStatement = connection
						.prepareStatement("UPDATE FILES SET ID_STORAGE = ? WHERE ID = ?")) {
			for (File files : fileArray){
				updateIdStorage(files, storage);
				//deleteFile(files);
			}
			getConnection().commit();
		} catch (SQLException errorTransaction) {
			getConnection().rollback();
			errorTransaction.printStackTrace();
			throw errorTransaction;
		}
	}
	
	public void deleteFile(File file) throws SQLException {
		try {
			new FileDAO().delete(file.getId());
			getConnection().commit();
		} catch (SQLException errorTransaction) {
			getConnection().rollback();
			errorTransaction.printStackTrace();
			throw errorTransaction;
		}
	}

	public static Connection getConnection() throws SQLException {
		if (connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			connection.setAutoCommit(false);
		}
		return connection;
	}

}
