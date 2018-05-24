package lessons_4_task;

import java.sql.SQLException;

public class ValidatorInputData {

	private boolean isFormatsEquals (Storage storage, File file) throws SQLException {
		for (String format : storage.getFormatsSupported()) {
			if (format.equals(file.getFormat())) {
				return true;
			}
		}
		throw new SQLException("File's format not equal Storage's format");
	}
	
	public boolean isIdNotInStorage (Storage storage, File file) throws SQLException {
		if (storage.getFiles() != null) {
			for (File files : storage.getFiles()) {
				if (files.getId() == file.getId()) {
					System.out.println("File's ID is used in Storage");
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean isStorageFull (Storage storage, File file) throws SQLException {
		if (storage.getFiles() != null) {
			long maxSizeStorage = 0;
			for (File files : storage.getFiles()) {
				maxSizeStorage += files.getSize();
			}
			if (maxSizeStorage + file.getSize() > storage.getStorageSize()) {
				throw new SQLException("File too big, Storage is full");
			}
		} else {
			if (storage.getStorageSize() < file.getSize()) {
				throw new SQLException("File too big");
			}
		}
		return true;
	}
	
	public boolean isInputDataCorrect(Storage storage, File file) throws SQLException {
		if (isFormatsEquals (storage, file) && isIdNotInStorage (storage, file) && isStorageFull (storage, file)) {
			return true;
		}
		return false;
	}

}
