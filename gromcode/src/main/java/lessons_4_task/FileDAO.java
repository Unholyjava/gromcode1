package lessons_4_task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FileDAO {

	private static final String UPDATE_FILES_BY_ID = "UPDATE FILES SET FILESNAME = ?, FILESFORMAT = ?, FILESSIZE = ? WHERE ID = ?";
	private static final String DELETE_FILES_BY_ID = "DELETE FROM FILES WHERE ID = ?";
	private static final String INSERT_INTO_FILES = "INSERT INTO FILES (ID, FILESNAME, FILESFORMAT, FILESSIZE) VALUES (?, ?, ?, ?)";
	private static final String SELECT_FILES_BY_ID = "SELECT * FROM FILES WHERE ID = ?";
	private static final String SELECT_FILES_BY_ID_STORAGE = "SELECT * FROM FILES WHERE ID_STORAGE = ?";

	public File save(File file) {
		try (Connection connection = CommonDAO.getConnection();
				PreparedStatement prepareStatementSelect = connection
						.prepareStatement(SELECT_FILES_BY_ID);
				PreparedStatement prepareStatement = connection
						.prepareStatement(INSERT_INTO_FILES)) {
			prepareStatementSelect.setLong(1, file.getId());
			if (prepareStatementSelect.executeQuery().next()) {
				throw new Exception("current id is used, save File not complete");
			}
			if (file.getName().length() > 10) {
				throw new Exception("too much lenght of File's name");
			}
			prepareStatement.setLong(1, file.getId());
			prepareStatement.setString(2, file.getName());
			prepareStatement.setString(3, file.getFormat());
			prepareStatement.setLong(4, file.getSize());
			int response = prepareStatement.executeUpdate();
			System.out.println("save File was finished with result " + response);
		} catch (SQLException e) {
			System.out.println(CommonDAO.ERROR);
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return file;
	}

	public void delete(long id) {
		try (Connection connection = CommonDAO.getConnection();
				PreparedStatement prepareStatementSelect = connection
						.prepareStatement(SELECT_FILES_BY_ID);
				PreparedStatement prepareStatement = connection
						.prepareStatement(DELETE_FILES_BY_ID)) {
			prepareStatementSelect.setLong(1, id);
			if (!prepareStatementSelect.executeQuery().next()) {
				throw new Exception("id not found, delete File not complete");
			}
			prepareStatement.setLong(1, id);
			int response = prepareStatement.executeUpdate();
			System.out.println("delete File was finished with result " + response);
		} catch (SQLException e) {
			System.out.println(CommonDAO.ERROR);
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public File update(File file) {
		try (Connection connection = CommonDAO.getConnection();
				PreparedStatement prepareStatementSelect = connection
						.prepareStatement(SELECT_FILES_BY_ID);
				PreparedStatement prepareStatement = connection
						.prepareStatement(UPDATE_FILES_BY_ID)) {
			prepareStatementSelect.setLong(1, file.getId());
			if (!prepareStatementSelect.executeQuery().next()) {
				throw new Exception("id not found, update File not complete");
			}
			if (file.getName().length() > 10) {
				throw new Exception("too much lenght of File's name");
			}
			prepareStatement.setLong(4, file.getId());
			prepareStatement.setString(1, file.getName());
			prepareStatement.setString(2, file.getFormat());
			prepareStatement.setLong(3, file.getSize());
			int response = prepareStatement.executeUpdate();
			System.out.println("update File was finished with result " + response);
		} catch (SQLException e) {
			System.out.println(CommonDAO.ERROR);
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return file;
	}
	
	public File findById(long id) {
		try (Connection connection = CommonDAO.getConnection();
				PreparedStatement prepareStatementSelect = connection
						.prepareStatement(SELECT_FILES_BY_ID)) {
			prepareStatementSelect.setLong(1, id);
			ResultSet resultSet = prepareStatementSelect.executeQuery();
			if (!resultSet.next()) {
				return null;
			} else {
				File file = new File(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getLong(4));
				return file;
			}
		} catch (SQLException e) {
			System.out.println(CommonDAO.ERROR);
			e.printStackTrace();
		}
		return null;
	}
	
	public File[] findByIdStorage(long id_storage) {
		try (Connection connection = CommonDAO.getConnection();
				PreparedStatement prepareStatementSelect = connection
						.prepareStatement(SELECT_FILES_BY_ID_STORAGE)) {
			prepareStatementSelect.setLong(1, id_storage);
			ResultSet resultSet = prepareStatementSelect.executeQuery();
			List<File> files = new ArrayList<>();
			if (!resultSet.next()) {
				return null;
			} else {
				while (resultSet.next()) {
					File file = new File(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getLong(4));
					files.add(file);
				}
				return files.toArray(new File[files.size()]);
			}
		} catch (SQLException e) {
			System.out.println(CommonDAO.ERROR);
			e.printStackTrace();
		}
		return null;
	}

}
