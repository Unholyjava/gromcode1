package lessons_core_19_2;

public class Controller {
	
	public File put(Storage storage, File file) throws Exception {
		if (ValidatorInputData.isInputDataCorrect(storage, file)) {
			for (int i = 0; i < storage.getFiles().length; ++i) {
				if (storage.getFiles()[i] == null) {
					storage.getFiles()[i] = file;
					return file;
				}
			}
			throw new Exception("Storage is full");
		} 
		throw new Exception("not put File with ID = " + file.getId() 
			+ " in to Storage with ID = " + storage.getId() + "\n");
	}

	public File delete(Storage storage, File file) throws Exception {
		if (ValidatorInputData.isIdInStorage(storage.getFiles(), file)) {
			for (int i = 0; i < storage.getFiles().length; ++i) {
				if (storage.getFiles()[i] == file) {
					storage.getFiles()[i] = null;
					return file;
				}
			}
		} 
		throw new Exception("not delete File with ID = " + file.getId() 
			+ " from Storage with ID = " + storage.getId() + "\n");
	}
	
	public void transferAll(Storage storageFrom, Storage storageTo) throws Exception {
		for (File file : storageFrom.getFiles()) {
			if (file != null) {
				if (ValidatorInputData.isInputDataCorrect(storageTo, file)) {
					put(storageTo, file);
					delete(storageFrom, file);
				} else {
					throw new Exception("not transfer all files from Storage with ID = " + storageFrom.getId() 
						+ " to Storage with ID = " + storageTo.getId() + "\n");
				}
			}
		}
	}
	
	public void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {
		File file = findFileInStorage(storageFrom, id); 
		if (ValidatorInputData.isInputDataCorrect(storageTo, file)) {
			put(storageTo, file);
			delete(storageFrom, file);
		} else {
			throw new Exception("not transfer File with ID = " + id 
					+ " in to Storage with ID = " + storageTo.getId()
					+ " from Storage with ID = " + storageFrom.getId() + "\n");
		}
	}
		
	private File findFileInStorage(Storage storage, long id) throws Exception {
		for (File files : storage.getFiles()) {
			if (files != null && files.getId() == id) {
				return files;
			}
		}
		throw new Exception("File " + id + " not found in Storage " + storage.getId());
	}
}
