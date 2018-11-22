package lessons_core_19_2;

public class Controller {
	
	public File put(Storage storage, File file) throws Exception {
		ValidatorInputData.isStorageAndFileValid(storage, file);
		for (int i = 0; i < storage.getFiles().length; ++i) {
			if (storage.getFiles()[i] == null) {
				storage.getFiles()[i] = file;
				return file;
			}
		}
		return null;
	}

	public File delete(Storage storage, File file) throws Exception {
		ValidatorInputData.isFileInStorage(storage.getFiles(), file, storage.getId()); 
		for (int i = 0; i < storage.getFiles().length; ++i) {
			if (storage.getFiles()[i].equals(file)) {
				storage.getFiles()[i] = null;
				return file;
			}
		}
		return null;
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
