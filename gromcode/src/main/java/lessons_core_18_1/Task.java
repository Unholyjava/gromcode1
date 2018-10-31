package lessons_core_18_1;

public class Task {
	
	public static void main(String[] args) {
		CodeStorage codeStorage = new CodeStorage();
		FileStorage fileStorage = new FileStorage();
		PictureStorage pictureStorage = new PictureStorage();
		String[] names1 = {"name1-1", "name1-2"};
		String[] names2 = {"name2-1", "name2-2", "name2-3", "name2-4", "name2-5"};
		String[] names3 = {"name3-1", "name3-2", "name3-3", "name3-4"};
		codeStorage.setFiles(names1);
		fileStorage.setFiles(names2);
		pictureStorage.setFiles(names3);
		printer(codeStorage);
		printer(fileStorage);
		printer(pictureStorage);
	}
	
	public static void printer(Storage storage) {
		String[] fileNames = storage.getFiles();
		try {
			System.out.println("5th name is " + fileNames[4]);
		} catch (Exception e) {
			System.out.println("5h name can not be found...");
		}
	}
}
