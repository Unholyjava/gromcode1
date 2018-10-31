package lessons_core_18_2;

public class Converter {
	
	public static void main(String[] args) {
		System.out.println(stringToInt("123"));
		System.out.println(intToString(12345));
		
		System.out.println(stringToInt("123qwer"));
		System.out.println(intToString(123456));
		
		System.out.println(stringToInt("012"));
		System.out.println(intToString(1234567890));
	}
	
	public static int stringToInt(String input) {
		try {
			return Integer.parseInt(input);
		} catch (Exception e) {
			System.out.println(input + " can not be converted to int");
			return 0;
		}
	}
	
	public static String intToString (int input) {
		return String.valueOf(input);
	}
}
