import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FindMyWords {

	/**
	 * Read file line by line
	 * 
	 * @param file to read
	 * @return list of words
	 * @throws FileNotFoundException
	 */
	List<String> readWords(File file) throws FileNotFoundException {
		List<String> result = new ArrayList<String>();
		Scanner sc = new Scanner(file);
		try {
			while (sc.hasNextLine()) {
				String data = sc.nextLine();
				if (!data.isEmpty()) {
					result.add(data);
				}
			}
		} finally {
			sc.close();
		}
		return result;
	}

	/**
	 * @param words,         where we search
	 * @param text,          which we search
	 * @param caseSensitive, check words for Uppercase
	 * @return counter of find words
	 */
	int countWords(List<String> words, String text, boolean caseSensitive) {
		int result = 0;
		for (String word : words) {
			result += containsString(text, word, caseSensitive);
		}
		return result;
	}

	/**
	 * @param text          - string, where we look
	 * @param sample        - string, what we look
	 * @param caseSensitive - check words for Uppercase
	 * @return true of text, which contains the sample
	 */
	int containsString(String text, String sample, boolean caseSensitive) {
		int result = 0;
		if (!caseSensitive) {
			text = text.toUpperCase();
			sample = sample.toUpperCase();
		}
		int curr = text.indexOf(sample, 0);
		while (curr != -1) {
			result++;
			curr = text.indexOf(sample, curr + 1);
		}
		return result;
	}

	
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("input.txt");
		FindMyWords obj = new FindMyWords();
		List<String> words = obj.readWords(file);
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		if (str.isEmpty()) {
			throw new RuntimeException("Empty line is not allowed");
		}
		int result = obj.countWords(words, str, true);
		System.out.println(result + " words found");
	}

}
