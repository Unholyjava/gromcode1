package lessons_core_19_2;

public class ValidatorInputData {

	private static boolean isFormatsEquals (Storage storage, File file) {
		for (String format : storage.getFormatsSupported()) {
			if (format.equals(file.getFormat())) {
				return true;
			}
		}
		throw new RuntimeException("File's format not equal Storage's format");
	}
	
	public static boolean isIdNotInStorage (Storage storage, File file) {
		if (storage.getFiles() != null) {
			for (File files : storage.getFiles()) {
				if (files.getId() == file.getId()) {
					throw new RuntimeException("File's ID is used in Storage");
				}
			}
		}
		return true;
	}
	
	public static boolean isIdInStorage (Storage storage, File file) {
		if (storage.getFiles() != null) {
			for (File files : storage.getFiles()) {
				if (files.getId() == file.getId()) {
					return true;
				}
			}
			throw new RuntimeException("File's ID is not used in Storage");
		}
		throw new RuntimeException("Storage is empty");
	}
	
	private static boolean isStorageFull (Storage storage, File file) {
		if (storage.getFiles() != null) {
			long maxSizeStorage = 0;
			for (File files : storage.getFiles()) {
				maxSizeStorage += files.getSize();
			}
			if (maxSizeStorage + file.getSize() > storage.getStorageSize()) {
				throw new RuntimeException("File too big, Storage will be full");
			}
		} else {
			if (storage.getStorageSize() < file.getSize()) {
				throw new RuntimeException("File too big, Storage will be full");
			}
		}
		return true;
	}
	
	public static boolean isInputDataCorrect(Storage storage, File file) {
		if (isFormatsEquals (storage, file) && isIdNotInStorage (storage, file) && isStorageFull (storage, file)) {
			return true;
		}
		return false;
	}
}
