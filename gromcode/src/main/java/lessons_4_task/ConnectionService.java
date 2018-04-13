package lessons_4_task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionService {

	private static final String DB_URL = "jdbc:oracle:thin:@gromcodegregorydb-lessons.cykue0lynxa0.us-east-2.rds.amazonaws.com:1521:ORCL";
	private static final String USER = "main";
	private static final String PASS = "shmopka1488";
	protected static final String ERROR = "Something went wrong";
	private Connection connection;
	
	public Connection getConnection() throws SQLException {
		if (connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			connection.setAutoCommit(false);
		}
		return connection;
	}

}
