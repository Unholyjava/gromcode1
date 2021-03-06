package lessons_4_task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StorageDAO {

	private static final String UPDATE_STORAGE_BY_ID = "UPDATE STORAGE SET FORMATSSUPPORTED = ?, STORAGECOUNTRY = ?, STORAGESIZE = ? WHERE ID = ?";
	private static final String DELETE_STORAGE_BY_ID = "DELETE FROM STORAGE WHERE ID = ?";
	private static final String INSERT_INTO_STORAGE = "INSERT INTO STORAGE VALUES (?, ?, ?, ?)";
	private static final String SELECT_STORAGE_BY_ID = "SELECT * FROM STORAGE WHERE ID = ?";

	public Storage save(Storage storage) {
		try (Connection connection = CommonDAO.getConnection();
				PreparedStatement prepareStatementSelect = connection
						.prepareStatement(SELECT_STORAGE_BY_ID);
				PreparedStatement prepareStatement = connection
						.prepareStatement(INSERT_INTO_STORAGE)) {
			prepareStatementSelect.setLong(1, storage.getId());
			if (prepareStatementSelect.executeQuery().next()) {
				throw new Exception("current id is used, save Storage not complete");
			}
			prepareStatement.setLong(1, storage.getId());
			prepareStatement.setString(2, storage.getStringFormatsSupported());
			prepareStatement.setString(3, storage.getStorageCountry());
			prepareStatement.setLong(4, storage.getStorageSize());
			int response = prepareStatement.executeUpdate();
			System.out.println("save Storage was finished with result " + response);
		} catch (SQLException e) {
			System.out.println(CommonDAO.ERROR);
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return storage;
	}

	public void delete(long id) {
		try (Connection connection = CommonDAO.getConnection();
				PreparedStatement prepareStatementSelect = connection
						.prepareStatement(SELECT_STORAGE_BY_ID);
				PreparedStatement prepareStatement = connection
						.prepareStatement(DELETE_STORAGE_BY_ID)) {
			prepareStatementSelect.setLong(1, id);
			if (!prepareStatementSelect.executeQuery().next()) {
				throw new Exception("id not found, delete Storage not complete");
			}
			prepareStatement.setLong(1, id);
			int response = prepareStatement.executeUpdate();
			System.out.println("delete Storage was finished with result " + response);
		} catch (SQLException e) {
			System.out.println(CommonDAO.ERROR);
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public Storage update(Storage storage) {
		try (Connection connection = CommonDAO.getConnection();
				PreparedStatement prepareStatementSelect = connection
						.prepareStatement(SELECT_STORAGE_BY_ID);
				PreparedStatement prepareStatement = connection
						.prepareStatement(UPDATE_STORAGE_BY_ID)) {
			prepareStatementSelect.setLong(1, storage.getId());
			if (!prepareStatementSelect.executeQuery().next()) {
				throw new Exception("id not found, update Storage not complete");
			}
			prepareStatement.setLong(4, storage.getId());
			prepareStatement.setString(1, storage.getStringFormatsSupported());
			prepareStatement.setString(2, storage.getStorageCountry());
			prepareStatement.setLong(3, storage.getStorageSize());
			int response = prepareStatement.executeUpdate();
			System.out.println("update Storage was finished with result " + response);
		} catch (SQLException e) {
			System.out.println(CommonDAO.ERROR);
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return storage;
	}
	
	public Storage findById(long id) {
		try (Connection connection = CommonDAO.getConnection();
				PreparedStatement prepareStatementSelect = connection
						.prepareStatement(SELECT_STORAGE_BY_ID)) {
			prepareStatementSelect.setLong(1, id);
			ResultSet resultSet = prepareStatementSelect.executeQuery();
			if (!resultSet.next()) {
				return null;
			} else {
				Storage storage = new Storage(resultSet.getLong(1), new FileDAO().findByIdStorage(id), resultSet.getString(2).split(", "), resultSet.getString(3), resultSet.getLong(4));
				return storage;
			}
		} catch (SQLException e) {
			System.out.println(CommonDAO.ERROR);
			e.printStackTrace();
		}
		return null;
	}
	
}
