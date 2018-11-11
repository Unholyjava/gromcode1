package lessons_core_19_2;

public class ValidatorInputData {

	private static boolean isFormatsEquals (String[] formats, File file) {
		for (String format : formats) {
			if (format.equals(file.getFormat())) {
				return true;
			}
		}
		throw new RuntimeException("File's format not equal Storage's format");
	}
	
	public static boolean isIdNotInStorage (File[] files, File file) {
		for (File currentFile : files) {
			if (currentFile != null && currentFile.getId() == file.getId()) {
				throw new RuntimeException("File's ID is used in Storage");
			}
		}
		return true;
	}
	
	public static boolean isIdInStorage (File[] files, File file) {
		for (File currentFile : files) {
			if (currentFile != null && currentFile.getId() == file.getId()) {
				return true;
			}
		}
		throw new RuntimeException("File's ID is not used in Storage");
	}
	
	private static boolean isStorageFull (Storage storage, File file) {
		long maxSizeStorage = 0;
		for (File files : storage.getFiles()) {
			if (files != null) {
				maxSizeStorage += files.getSize();
			}
		}
		if (maxSizeStorage + file.getSize() > storage.getStorageSize()) {
			throw new RuntimeException("File too big, Storage will be full");
		}
		return true;
	}
	
	public static boolean isInputDataCorrect(Storage storage, File file) {
		if (isFormatsEquals (storage.getFormatsSupported(), file) && isIdNotInStorage (storage.getFiles(), file) && isStorageFull (storage, file)) {
			return true;
		}
		return false;
	}
}
