package lessons_core_19_2;

import java.sql.SQLException;

public class Demo {

	public static void main(String[] args) throws SQLException {
		File file1 = new File(11, "file1", "txt", 100);
		File file2 = new File(12, "file2", "jpg", 500);
		File file3 = new File(13, "file3", "jpg", 300);
		File file4 = new File(14, "file3", "jpg", 300);
		File file5 = new File(15, "file3", "jpg", 300);
			
		Storage storage1 = new Storage(1, null, new String[]{"txt", "jpg"}, "Ukraine", 700);
		Storage storage2 = new Storage(2, null, new String[]{"txt", "tif"}, "Ukraine", 700);
		Storage storage3 = new Storage(3, null, new String[]{"txt", "jpg", "tif"}, "Ukraine", 300);
		Storage storage4 = new Storage(4, new File[]{file4}, new String[]{"txt", "jpg", "tif"}, "Ukraine", 500);
		Storage storage5 = new Storage(5, new File[]{file3, file5}, new String[]{"txt", "jpg", "tif"}, "Ukraine", 1000);
		Storage storage6 = new Storage(6, new File[]{file4}, new String[]{"txt", "jpg", "tif"}, "Ukraine", 500);
		Storage storage7 = new Storage(7, new File[]{file3, file5}, new String[]{"txt", "jpg", "tif"}, "Ukraine", 1000);
		
		Controller controller = new Controller();
		
		controller.put(storage1, file1);	//ok put
		controller.put(storage1, file2);
		controller.put(storage2, file2);	//error put ("File's format not equal Storage's format")
		controller.put(storage4, file4);	//error put ("File's ID is used in Storage")
		controller.put(storage4, file5);	//error put ("File too big, Storage will be full")
		controller.put(storage3, file2);	//error put ("File too big, Storage will be full")
		
		controller.delete(storage4, file4); //ok delete
		controller.delete(storage2, file2); //error delete ("Storage is empty")
		controller.delete(storage4, file4); //error delete ("File's ID is not used in Storage")
		
		controller.transferAll(storage5, storage2); //error transferALL ("File's format not equal Storage's format")
		controller.transferAll(storage5, storage4); //error transferALL ("File too big, Storage will be full")
		controller.transferAll(storage4, storage5); //ok transferALL
		
		controller.transferFile(storage7, storage6, 11); //error transferFile ("not valid input data")
		controller.transferFile(storage6, storage7, 11); //error transferFile ("not valid input data")
		controller.transferFile(storage6, storage7, 14); //ok transferFile
		
		System.out.println("Congratulations!");
	}
}
