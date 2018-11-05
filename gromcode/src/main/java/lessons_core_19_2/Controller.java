package lessons_core_19_2;

import java.util.Arrays;

public class Controller {
	
	public File put(Storage storage, File file) throws Exception {
		if (ValidatorInputData.isInputDataCorrect(storage, file)) {
			storage.setFiles(addToFileArray(storage.getFiles(), file));
			return file;
		} 
		throw new Exception("not put File with ID = " + file.getId() 
			+ " in to Storage with ID = " + storage.getId() + "\n");
	}

	public File delete(Storage storage, File file) throws Exception {
		if (ValidatorInputData.isIdInStorage(storage, file)) {
			storage.setFiles(removeFileFromArray(storage.getFiles(), file));
			return file;
		} 
		throw new Exception("not delete File with ID = " + file.getId() 
			+ " from Storage with ID = " + storage.getId() + "\n");
	}
	
	public void transferAll(Storage storageFrom, Storage storageTo) throws Exception {
		for (File file : storageFrom.getFiles()) {
			if (ValidatorInputData.isInputDataCorrect(storageTo, file)) {
				storageTo.setFiles(addToFileArray(storageTo.getFiles(), file));
				storageFrom.setFiles(removeFileFromArray(storageFrom.getFiles(), file));
			} else {
				throw new Exception("not transfer all files from Storage with ID = " + storageFrom.getId() 
				+ " to Storage with ID = " + storageTo.getId() + "\n");
			}
		}
	}
	
	public void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {
		File file = findFileInStorage(storageFrom, id); 
		if (file != null && ValidatorInputData.isInputDataCorrect(storageTo, file)) {
			storageTo.setFiles(addToFileArray(storageTo.getFiles(), file));
			storageFrom.setFiles(removeFileFromArray(storageFrom.getFiles(), file));
		} else {
			throw new Exception("not transfer File with ID = " + id 
					+ " in to Storage with ID = " + storageTo.getId()
					+ " from Storage with ID = " + storageFrom.getId() + "\n");
		}
	}
	
	private File[] addToFileArray(File[] oldFileArray, File newFile) {
		if (oldFileArray == null) {
			return new File[]{newFile};
		} else {
			File[] newFileArray = Arrays.copyOf(oldFileArray, oldFileArray.length + 1);
			newFileArray[oldFileArray.length] = newFile;
			return newFileArray;
		}
	}
	
	private File[] removeFileFromArray(File[] oldFileArray, File removeFile) {
		for (int i = 0; i < oldFileArray.length; ++i) {
			if (oldFileArray[i] == removeFile) {
				File[] newFileArray = new File[oldFileArray.length - 1];
				System.arraycopy(oldFileArray, 0, newFileArray, 0, i);
				System.arraycopy(oldFileArray, i + 1, newFileArray, i, oldFileArray.length - i - 1);
				return newFileArray;
			}
		}
		return null;
	}
	
	private File findFileInStorage(Storage storage, long id) throws Exception {
		for (File files : storage.getFiles()) {
			if (files.getId() == id) {
				return files;
			}
		}
		System.out.println("File " + id + " not found in Storage " + storage.getId());
		return null;
	}
}
