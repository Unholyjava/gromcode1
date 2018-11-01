package lessons_core_18_4;

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
			if (word.matches("\\d+")) {
				intWords[i] = Integer.parseInt(word);
				i =+ 1;
			} else {
				System.out.println("not a number");
			}
		}
		return intWords;
	}
}
