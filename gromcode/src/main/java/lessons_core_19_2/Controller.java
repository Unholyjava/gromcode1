package lessons_core_19_2;

public class Controller {
	
	public File put(Storage storage, File file) throws Exception {
		ValidatorInputData.isStorageAndFileValid(storage, file);
		int index = 0;
		for (File fileCurrent : storage.getFiles()) {
			if (fileCurrent == null) {
				storage.getFiles()[index] = file;
				return file;
			}
			index++;
		}
		return null;
	}

	public void delete(Storage storage, File file) throws Exception {
		ValidatorInputData.isFileInStorage(storage.getFiles(), file, storage.getId()); 
		int index = 0;
		for (File fileCurrent : storage.getFiles()) {
			if (fileCurrent != null && fileCurrent.equals(file)) {
				storage.getFiles()[index] = null;
				return;
			}
			index++;
		}
	}
	
	public void transferAll(Storage storageFrom, Storage storageTo) throws Exception {
		for (File file : storageFrom.getFiles()) {
			if (file != null) {
				put(storageTo, file);
				delete(storageFrom, file);
			}	
		}
	}
	
	public void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {
		File file = ValidatorInputData.findFileInStorage(storageFrom.getFiles(), id, storageFrom.getId()); 
		put(storageTo, file);
		delete(storageFrom, file);
	}
}
