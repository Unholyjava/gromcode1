package lessons_core_18_3;

public class Solution {
	
//	public static void main(String[] args) {
//		int[] numbers = findNumbers("123 wer wer1 456");
//		for (int number : numbers) {
//			System.out.println(number);
//		}
//	}
	
	public static int[] findNumbers(String text) {
		String[] textWords = text.split(" ");
		int[] intWords = new int[textWords.length];
		int i = 0;
		for (String word : textWords) {
			try {
				intWords[i] = Integer.parseInt(word);
			} catch (NumberFormatException e) {
				System.out.println("not a number");
			}
			i =+ 1;
		}
		return intWords;
	}
}
