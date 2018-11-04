package lessons_core_19_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {
	
	public void put(Storage storage, File file) {
		try {
			if (ValidatorInputData.isInputDataCorrect(storage, file)) {
				List<File> arrayList = arrayToArrayList(storage);
				arrayList.add(file);
				arrayListToArray(storage, arrayList);
			}
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			System.out.println("not put File with ID = " + file.getId() 
				+ " in to Storage with ID = " + storage.getId() + "\n");
		} 
	}

	public void delete(Storage storage, File file) {
		try {
			if (ValidatorInputData.isIdInStorage(storage, file)) {
				List<File> arrayList = arrayToArrayList(storage);
				arrayList.remove(file);
				arrayListToArray(storage, arrayList);
			} 
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			System.out.println("not delete File with ID = " + file.getId() 
				+ " from Storage with ID = " + storage.getId() + "\n");
		} 
	}
	
	public void transferAll(Storage storageFrom, Storage storageTo) {
		try {
			for (File file : storageFrom.getFiles()) {
				if (ValidatorInputData.isInputDataCorrect(storageTo, file)) {
					List<File> arrayListFrom = arrayToArrayList(storageFrom);
					List<File> arrayListTo = arrayToArrayList(storageTo);
					arrayListTo.add(file);
					arrayListFrom.remove(file);
					arrayListToArray(storageTo, arrayListTo);
					arrayListToArray(storageFrom, arrayListFrom);
				}
			}
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			System.out.println("not transfer all files from Storage with ID = " + storageFrom.getId() 
				+ " to Storage with ID = " + storageTo.getId() + "\n");
		} 
	}
	
	public void transferFile(Storage storageFrom, Storage storageTo, long id) {
		try {
			File file = findFileInStorage(storageFrom, id); 
			if (ValidatorInputData.isInputDataCorrect(storageTo, file)) {
				List<File> arrayListFrom = arrayToArrayList(storageFrom);
				List<File> arrayListTo = arrayToArrayList(storageTo);
				arrayListTo.add(file);
				arrayListFrom.remove(file);
				arrayListToArray(storageTo, arrayListTo);
				arrayListToArray(storageFrom, arrayListFrom);
			}
			
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			System.out.println("not transfer File with ID = " + id 
				+ " in to Storage with ID = " + storageTo.getId()
				+ " from Storage with ID = " + storageFrom.getId() + "\n");
		} 
	}
	
	private void arrayListToArray(Storage storage, List<File> arrayList) {
		File[] newFile = new File[arrayList.size()];
		arrayList.toArray(newFile);
		storage.setFiles(newFile);
	}
	
	private List<File> arrayToArrayList(Storage storage) {
		List<File> arrayList;
		if (storage.getFiles() != null) {
			arrayList = new ArrayList<>(Arrays.asList(storage.getFiles()));
		} else {
			arrayList = new ArrayList<>();
		}
		return arrayList;
	}
	
	private File findFileInStorage(Storage storage, long id) {
		for (File files : storage.getFiles()) {
			if (files.getId() == id) {
				return files;
			}
		}
		throw new RuntimeException("File " + id + " not found in Storage " + storage.getId());
	}
}
