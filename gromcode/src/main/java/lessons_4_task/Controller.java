package lessons_4_task;

import java.sql.SQLDataException;
import java.sql.SQLException;

public class Controller {
	
	public void put(Storage storage, File file) throws SQLException {
		CommonDAO commonDao = new CommonDAO();
		try {
			if (isInputDataCorrect(storage, file)) {
				commonDao.updateIdStorage(file, storage);
			}
		} catch (SQLException e) {
			System.out.println("not put File with ID = " + file.getId() 
				+ " in to Storage with ID = " + storage.getId());
			e.printStackTrace();
		}
	}
	
	private boolean isFormatsEquals (Storage storage, File file) throws SQLDataException {
		for (String format : storage.getFormatsSupported()) {
			if (format.equals(file.getFormat())) {
				return true;
			}
		}
		throw new SQLDataException("File's format not equal Storage's format");
	}
	
	private boolean isIdInStorage (Storage storage, File file) throws SQLDataException {
		if (storage.getFiles() != null) {
			for (File files : storage.getFiles()) {
				if (files.getId() == file.getId()) {
					throw new SQLDataException("File's ID is used in Storage");
				}
			}
		}
		return true;
	}
	
	private boolean isStorageFull (Storage storage, File file) throws SQLDataException {
		if (storage.getFiles() != null) {
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
	
	private boolean isInputDataCorrect(Storage storage, File file) throws SQLDataException {
		if (isFormatsEquals (storage, file) || isIdInStorage (storage, file) || isStorageFull (storage, file)) {
			return true;
		}
		return false;
	}
			
}
