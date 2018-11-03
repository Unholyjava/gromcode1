package lessons_core_19_1;

public class ThrowExample {
	
	private static String[] arrayString = {"one", "two", "three", null, "four", "five"};
	
	public static void main(String[] args) {
		useOfTestMethod();
	}
	
	private static void test() {
		for (String element : arrayString) {
			if (element == null) {
				throw new RuntimeException("null is detected");
			}
		}
		System.out.println("done");
	}
	
	private static void useOfTestMethod() {
		try {
			//some code
			test();
			//some code
		} catch (Exception e) {
			System.out.println("error: " + e.getMessage());
		}
	}
}
