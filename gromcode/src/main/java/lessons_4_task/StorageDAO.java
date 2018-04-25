package lessons_4_task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;

public class StorageDAO {

	public Storage save(Storage storage) {
		try (Connection connection = CommonDAO.getConnection();
				PreparedStatement prepareStatementSelect = connection
						.prepareStatement("SELECT * FROM STORAGE WHERE ID = ?");
				PreparedStatement prepareStatement = connection
						.prepareStatement("INSERT INTO STORAGE VALUES (?, ?, ?, ?)")) {
			prepareStatementSelect.setLong(1, storage.getId());
			if (prepareStatementSelect.executeQuery().next()) {
				System.out.println("current id is used, save Storage not complete");
				return storage;
			}
			prepareStatement.setLong(1, storage.getId());
			prepareStatement.setString(2, storage.getStringFormatsSupported());
			prepareStatement.setString(3, storage.getStorageCountry());
			prepareStatement.setLong(4, storage.getStorageSize());
			int response = prepareStatement.executeUpdate();
			System.out.println("save Storage was finished with result " + response);
		} catch (SQLDataException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			System.out.println(CommonDAO.ERROR);
			se.printStackTrace();
		}
		return storage;
	}

	public void delete(long id) {
		try (Connection connection = CommonDAO.getConnection();
				PreparedStatement prepareStatementSelect = connection
						.prepareStatement("SELECT * FROM STORAGE WHERE ID = ?");
				PreparedStatement prepareStatement = connection
						.prepareStatement("DELETE FROM STORAGE WHERE ID = ?")) {
			prepareStatementSelect.setLong(1, id);
			if (!prepareStatementSelect.executeQuery().next()) {
				throw new SQLDataException("id not found, delete Storage not complete");
			}
			prepareStatement.setLong(1, id);
			int response = prepareStatement.executeUpdate();
			System.out.println("delete Storage was finished with result " + response);
		} catch (SQLDataException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			System.out.println(CommonDAO.ERROR);
			se.printStackTrace();
		}
	}
	
	public Storage update(Storage storage) {
		try (Connection connection = CommonDAO.getConnection();
				PreparedStatement prepareStatementSelect = connection
						.prepareStatement("SELECT * FROM STORAGE WHERE ID = ?");
				PreparedStatement prepareStatement = connection
						.prepareStatement("UPDATE STORAGE SET FORMATSSUPPORTED = ?, STORAGECOUNTRY = ?, STORAGESIZE = ? WHERE ID = ?")) {
			prepareStatementSelect.setLong(1, storage.getId());
			if (!prepareStatementSelect.executeQuery().next()) {
				throw new SQLDataException("id not found, update Storage not complete");
			}
			prepareStatement.setLong(4, storage.getId());
			prepareStatement.setString(1, storage.getStringFormatsSupported());
			prepareStatement.setString(2, storage.getStorageCountry());
			prepareStatement.setLong(3, storage.getStorageSize());
			int response = prepareStatement.executeUpdate();
			System.out.println("update Storage was finished with result " + response);
		} catch (SQLDataException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			System.out.println(CommonDAO.ERROR);
			se.printStackTrace();
		}
		return storage;
	}
	
	public Storage findById(long id) {
		try (Connection connection = CommonDAO.getConnection();
				PreparedStatement prepareStatementSelect = connection
						.prepareStatement("SELECT * FROM STORAGE WHERE ID = ?")) {
			prepareStatementSelect.setLong(1, id);
			ResultSet resultSet = prepareStatementSelect.executeQuery();
			if (!resultSet.next()) {
				//throw new SQLDataException("id Storage not found");
				System.out.println("id Storage not found");
			} else {
				Storage storage = new Storage(resultSet.getLong(1), new FileDAO().findByIdStorage(id), resultSet.getString(2).split(", "), resultSet.getString(3), resultSet.getLong(4));
				return storage;
			}
			
		} catch (SQLDataException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			System.out.println(CommonDAO.ERROR);
			se.printStackTrace();
		}
		return null;
	}
	
}
