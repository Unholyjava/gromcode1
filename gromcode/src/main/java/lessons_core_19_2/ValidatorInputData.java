package lessons_core_19_2;

public class ValidatorInputData {

	public static void isFormatsEquals (String[] formats, File file, long storageId) throws Exception {
		for (String format : formats) {
			if (format.equals(file.getFormat())) {
				return;
			}
		}
		throw new Exception("File's format, ID = " + file.getId() 
			+ " not equal Storage's format, ID = " + storageId + "\n");
	}
	
	public static void isIdNotInStorage (File[] files, File file, long storageId) throws Exception {
		for (File currentFile : files) {
			if (currentFile != null && currentFile.getId() == file.getId()) {
				throw new Exception("File's ID = " + file.getId() 
					+ " is used in Storage, ID = " + storageId + "\n");
			}
		}
	}
	
	public static void isIdAndNameInStorage (File[] files, File file, long storageId) throws Exception {
		for (File currentFile : files) {
			if (currentFile != null && currentFile.getId() == file.getId() 
				&& currentFile.getName().equals(file.getName())) {
				return;
			}
		}
		throw new Exception("File's ID = " + file.getId() 
			+ " is not used in Storage, ID = " + storageId + "\n");
	}
	
	public static void isStorageMaxSizeFull (Storage storage, File file) throws Exception {
		long maxSizeStorage = 0;
		for (File files : storage.getFiles()) {
			if (files != null) {
				maxSizeStorage += files.getSize();
			}
		}
		if (maxSizeStorage + file.getSize() > storage.getStorageSize()) {
			throw new Exception("File, ID = " + file.getId() + " too big, Storage, ID = " 
				+ storage.getId() + " will be full\n");
		}
	}
	
	public static void isStorageFull (File[] files, long storageId) throws Exception {
		for (File file : files) {
			if (file == null) {
				return;
			}
		}
		throw new Exception("Storage, ID = " + storageId + " is full\n");
	}
	
	public static File findFileInStorage(File[] files, long fileId, long storageId) throws Exception {
		for (File file : files) {
			if (file != null && file.getId() == fileId) {
				return file;
			}
		}
		throw new Exception("File, ID = " + fileId + " not found in Storage, ID = " + storageId + "\n");
	}
}
