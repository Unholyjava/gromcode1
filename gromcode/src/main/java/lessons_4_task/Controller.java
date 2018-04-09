package lessons_4_task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.SQLException;

public class Controller {
	
	private static final String DB_URL = "jdbc:oracle:thin:@gromcodegregorydb-lessons.cykue0lynxa0.us-east-2.rds.amazonaws.com:1521:ORCL";

	private static final String USER = "main";
	private static final String PASS = "shmopka1488";
	private static final String ERROR = "Transaction error";

	public void put(Storage storage, File file) {
		try (Connection connection = getConnection()){
			FileDAO fileDao = new FileDAO();
			StorageDAO storageDao = new StorageDAO();
			if (putAllow(storage, file)) {
				connection.setAutoCommit(false);
				if (fileDao.findById(file.getId()) == null) {
					fileDao.save(file);
				}
				if (storageDao.findById(storage.getId()) == null) {
					storageDao.save(storage);
				}
				storageDao.setFileArray(file, storage);
				fileDao.updateIdStorage(file, storage);
				connection.commit();
			}
		} catch (SQLDataException de) {
			System.out.println(de);
			System.out.println("not put File with ID = " + file.getId() 
			+ " in to Storage with ID = " + storage.getId());
			
		} catch (SQLException e) {
			System.out.println(ERROR);
			e.printStackTrace();
		}
	}
	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL, USER, PASS);
	}
	
	private boolean putAllow(Storage storage, File file) throws SQLDataException {
		boolean isFormatEquals = false;
		for (String format : storage.getFormatsSupported()) {
			if (format.equals(file.getFormat())) {
				isFormatEquals = true;
				break;
			}
		}
		if (!isFormatEquals) {
			throw new SQLDataException("File's format not equal Storage's format");
		}
		
		if (storage.getFiles() != null) {
			for (File files : storage.getFiles()) {
				if (files.getId() == file.getId()) {
					throw new SQLDataException("File's ID is used in Storage");
				}
			}
		
			long maxSizeStorage = 0;
			for (File files : storage.getFiles()) {
				maxSizeStorage += files.getSize();
			}
			if (maxSizeStorage + file.getSize() > storage.getStorageSize()) {
				throw new SQLDataException("File too big, Storage is full");
			}
		} else {
			if (storage.getStorageSize() < file.getSize()) {
				throw new SQLDataException("File too big");
			}
		}
		
		return true;
	}
			
}
