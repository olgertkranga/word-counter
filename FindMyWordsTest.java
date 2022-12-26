import static org.junit.Assert.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

public class FindMyWordsTest {

	private static String SAMPLE_TEXT1 = "djkhofjkhacatalogdsahf";
	private static String SAMPLE_TEXT2 = "logloglogasdadasofasadslog";
	private static String SAMPLE_TEXT3 = "logLogloGasdadasOFasadsLOG";
	private static List<String> WORDS1 = Collections.unmodifiableList(Arrays.asList("can", "cat", "dog", "catalog", "log", "able", "of", "an"));
	private static List<String> WORDS2 = Collections.unmodifiableList(Arrays.asList("fog", "tiger", "cow", "duck", "pig"));
	
	@Test
	public void testReadWords() throws FileNotFoundException {
		File file = new File("input.txt");
		FindMyWords obj = new FindMyWords();
		List<String> result = obj.readWords(file);
		List<String> empty = new ArrayList<String>();
		assertEquals(WORDS1, result);
		assertNotEquals(WORDS2, result);
		file = new File("input_empty.txt");
		result = obj.readWords(file);
		assertEquals(empty, result);
	}

	
	@Test(expected = FileNotFoundException.class)
	public void testReadWordsFileNotFind() throws FileNotFoundException {
		File file = new File("file_not_find.txt");
		FindMyWords obj = new FindMyWords();
		obj.readWords(file);
		fail("expecting exception here");
	}

	
	@Test
	public void testCountWords() {
		FindMyWords obj = new FindMyWords();
		int result = obj.countWords(WORDS1, SAMPLE_TEXT1, true);
		assertEquals(result, 4);
		result = obj.countWords(WORDS1, SAMPLE_TEXT2, true);
		assertEquals(result, 5);
	}

	
	@Test
	public void testContainsString() {
		FindMyWords obj = new FindMyWords();
		int result = obj.containsString(SAMPLE_TEXT1, WORDS1.get(0), true);
		assertEquals(0, result);
		result = obj.containsString(SAMPLE_TEXT1, WORDS1.get(4), true);
		assertEquals(1, result);
		result = obj.containsString(SAMPLE_TEXT2, WORDS1.get(4), true);
		assertEquals(4, result);
	}

	
	@Test
	public void testContainsStringCaseInsensitive() {
		FindMyWords obj = new FindMyWords();
		int result = obj.containsString(SAMPLE_TEXT3, WORDS1.get(4), false);
		assertEquals(4, result);
	}

}
