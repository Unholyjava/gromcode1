package lessons_4_task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FileDAO extends ConnectionService {

	public File save(File file) {
		try (Connection connection = getConnection();
				PreparedStatement prepareStatementSelect = connection
						.prepareStatement("SELECT * FROM FILES WHERE ID = ?");
				PreparedStatement prepareStatement = connection
						.prepareStatement("INSERT INTO FILES (ID, FILESNAME, FILESFORMAT, FILESSIZE) VALUES (?, ?, ?, ?)")) {
			prepareStatementSelect.setLong(1, file.getId());
			if (prepareStatementSelect.executeQuery().next()) {
				throw new SQLDataException("current id is used, save File not complete");
			}
			if (file.getName().length() > 10) {
				throw new SQLDataException("too much lenght of File's name");
			}
			prepareStatement.setLong(1, file.getId());
			prepareStatement.setString(2, file.getName());
			prepareStatement.setString(3, file.getFormat());
			prepareStatement.setLong(4, file.getSize());
			int response = prepareStatement.executeUpdate();
			System.out.println("save File was finished with result " + response);
		} catch (SQLDataException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			System.out.println(ERROR);
			se.printStackTrace();
		}
		return file;
	}

	public void delete(long id) {
		try (Connection connection = getConnection();
				PreparedStatement prepareStatementSelect = connection
						.prepareStatement("SELECT * FROM FILES WHERE ID = ?");
				PreparedStatement prepareStatement = connection
						.prepareStatement("DELETE FROM FILES WHERE ID = ?")) {
			prepareStatementSelect.setLong(1, id);
			if (!prepareStatementSelect.executeQuery().next()) {
				throw new SQLDataException("id not found, delete File not complete");
			}
			prepareStatement.setLong(1, id);
			int response = prepareStatement.executeUpdate();
			System.out.println("delete File was finished with result " + response);
		} catch (SQLDataException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			System.out.println(ERROR);
			se.printStackTrace();
		}
	}
	
	public File update(File file) {
		try (Connection connection = getConnection();
				PreparedStatement prepareStatementSelect = connection
						.prepareStatement("SELECT * FROM FILES WHERE ID = ?");
				PreparedStatement prepareStatement = connection
						.prepareStatement("UPDATE FILES SET FILESNAME = ?, FILESFORMAT = ?, FILESSIZE = ? WHERE ID = ?")) {
			prepareStatementSelect.setLong(1, file.getId());
			if (!prepareStatementSelect.executeQuery().next()) {
				throw new SQLDataException("id not found, update File not complete");
			}
			if (file.getName().length() > 10) {
				throw new SQLDataException("too much lenght of File's name");
			}
			prepareStatement.setLong(4, file.getId());
			prepareStatement.setString(1, file.getName());
			prepareStatement.setString(2, file.getFormat());
			prepareStatement.setLong(3, file.getSize());
			int response = prepareStatement.executeUpdate();
			System.out.println("update File was finished with result " + response);
		} catch (SQLDataException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			System.out.println(ERROR);
			se.printStackTrace();
		}
		return file;
	}
	
	public File findById(long id) {
		try (Connection connection = getConnection();
				PreparedStatement prepareStatementSelect = connection
						.prepareStatement("SELECT * FROM FILES WHERE ID = ?")) {
			prepareStatementSelect.setLong(1, id);
			ResultSet resultSet = prepareStatementSelect.executeQuery();
			if (!resultSet.next()) {
				//throw new SQLDataException("id File not found");
				System.out.println("id File not found");
			} else {
				File file = new File(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getLong(4));
				return file;
			}
			
		} catch (SQLDataException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			System.out.println(ERROR);
			se.printStackTrace();
		}
		return null;
	}
	
	public void updateIdStorage(File file, Storage storage) {
		try (Connection connection = getConnection();
				PreparedStatement prepareStatement = connection
						.prepareStatement("UPDATE FILES SET ID_STORAGE = ? WHERE ID = ?")) {
			prepareStatement.setLong(2, file.getId());
			prepareStatement.setLong(1, storage.getId());
			int response = prepareStatement.executeUpdate();
			System.out.println("update IdStorage was finished with result " + response);
		} catch (SQLDataException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			System.out.println(ERROR);
			se.printStackTrace();
		}
	}
	
	public void setFileArray(File file, Storage storage) {
		File[] fileArrayNew;
		if (findByIdStorage(storage.getId()) != null) {
			fileArrayNew = new File[findByIdStorage(storage.getId()).length + 1];
			System.arraycopy(findByIdStorage(storage.getId()), 0, fileArrayNew, 0, findByIdStorage(storage.getId()).length);
			fileArrayNew[findByIdStorage(storage.getId()).length] = file;
		} else {
			fileArrayNew = new File[]{file};
		}
		storage.setFiles(fileArrayNew);
	}
	
	public File[] findByIdStorage(long id_storage) {
		try (Connection connection = getConnection();
				PreparedStatement prepareStatementSelect = connection
						.prepareStatement("SELECT * FROM FILES WHERE ID_STORAGE = ?")) {
			prepareStatementSelect.setLong(1, id_storage);
			ResultSet resultSet = prepareStatementSelect.executeQuery();
			List<File> files = new ArrayList<>();
			while (resultSet.next()) {
				File file = new File(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getLong(4));
				files.add(file);
			}
			return files.toArray(new File[files.size()]);
			
		} catch (SQLDataException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			System.out.println(ERROR);
			se.printStackTrace();
		}
		return null;
	}

}
