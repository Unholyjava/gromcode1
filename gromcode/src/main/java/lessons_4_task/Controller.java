package lessons_4_task;

import java.sql.SQLDataException;
import java.sql.SQLException;

public class Controller {
	
	public void put(Storage storage, File file) throws SQLException {
		FileDAO fileDao = new FileDAO();
		StorageDAO storageDao = new StorageDAO();
		try {
			if (isInputDataCorrect(storage, file)) {
				fileDao.save(file);
				storageDao.save(storage);
				fileDao.setPlusFileArray(file, storage);
				fileDao.updateIdStorage(file, storage);
				FileDAO.getConnection().commit();
			}
		} catch (SQLException e) {
			FileDAO.getConnection().rollback();
			System.out.println(e);
			System.out.println("not put File with ID = " + file.getId() 
			+ " in to Storage with ID = " + storage.getId());
		}
	}
	
	private boolean isInputDataCorrect(Storage storage, File file) throws SQLDataException {
		for (int i = 0; i < storage.getFormatsSupported().length; ++i) {
			if (storage.getFormatsSupported()[i].equals(file.getFormat())) {
				break;
			} else {
				if (i == storage.getFormatsSupported().length - 1) {
					throw new SQLDataException("File's format not equal Storage's format");
				}
			}
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
