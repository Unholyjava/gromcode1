package lessons_4_task;

import java.sql.SQLException;

public class Demo {

	public static void main(String[] args) throws SQLException {
		File file1 = new File(11, "file2", "txt", 100);
		//File file2 = new File(12, "file2", "jpg", 500);
		//File file3 = new File(13, "file3", "tif", 300);
		Storage storage1 = new Storage(1, new String[]{"txt", "jpg"}, "Ukraine", 600);
		Controller controller = new Controller();
		controller.put(storage1, file1);
		//controller.put(storage1, file2);
		//controller.put(storage1, file3);
		System.out.println(storage1.getFiles().length);
	}
}
