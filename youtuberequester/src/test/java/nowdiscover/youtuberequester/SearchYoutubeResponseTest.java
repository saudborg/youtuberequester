package nowdiscover.youtuberequester;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import nowdiscover.youtuberequester.domain.FrequencyWord;
import nowdiscover.youtuberequester.service.FrequencyWordService;
import nowdiscover.youtuberequester.service.FrequencyWordServiceImpl;

/**
 * Test the response from Youtube request is the same
 * @author sauloborges
 *
 */
public class SearchYoutubeResponseTest {

	private FrequencyWordService frequencyWordService;

	@Before
	public void initialize() {
		frequencyWordService = new FrequencyWordServiceImpl();
	}

	/**
	 * This test is to confirm that the word pig is always the most comum
	 * @throws IOException
	 */
	@Test
	public void searchForPig() throws IOException {
		List<FrequencyWord> frequencyByParam = frequencyWordService.getFrequencyByParam("pig", 5);
		// get the first result. Have to be "pig"
		assertEquals("First Result is different", "pig", frequencyByParam.get(0).getWord());
	}

	/**
	 * This test is to confirm that the word duck is always the most comum, doesn't important the max number of results
	 * @throws IOException
	 */
	@Test
	public void searchForDuck() throws IOException {
		List<FrequencyWord> frequencyByParam = frequencyWordService.getFrequencyByParam("duck", 500);
		// get the first result. Have to be "duck"
		assertEquals("First Result is different", "duck", frequencyByParam.get(0).getWord());
	}

	/**
	 * In this test
	 * 1 - Get all the words when you request the word pig
	 * 2 - Get the words that appear offten
	 * 3 - The word choosed in parameter musn't be in these words
	 * @throws IOException
	 */
	@Test
	public void searchForPigWords() throws IOException {
		String param = "pig";
		Integer results = 5;
		List<FrequencyWord> frequencyByParam = frequencyWordService.getFrequencyByParam(param, results);
		List<FrequencyWord> frequencyDiscriminate = frequencyWordService.getFrequencyDiscriminate(frequencyByParam,
				param, results);

		for (FrequencyWord frequencyWord : frequencyDiscriminate) {
			assertNotEquals("Result musn't contain the same that the param", param, frequencyWord.getWord());
		}
	}

	@Test
	public void searchForDuckWords() throws IOException {
		String param = "duck";
		Integer results = 10;
		List<FrequencyWord> frequencyByParam = frequencyWordService.getFrequencyByParam(param, results);
		List<FrequencyWord> frequencyDiscriminate = frequencyWordService.getFrequencyDiscriminate(frequencyByParam,
				param, results);

		for (FrequencyWord frequencyWord : frequencyDiscriminate) {
			assertNotEquals("Result musn't contain the same that the param", param, frequencyWord.getWord());
		}
	}

	/**
	 * In this test
	 * 1 - Get all the words when you request the word pig
	 * 2 - Get the words that appear offten
	 * 3 - Using each word, search in Youtube API again
	 * 4 - Assert if the word in parameter appear in new result
	 * @throws IOException
	 */
	@Test
	public void searchForPigWordsRevert() throws IOException {
		String param = "pig";
		Integer results = 5;
		List<FrequencyWord> frequencyByParam = frequencyWordService.getFrequencyByParam(param, results);
		List<FrequencyWord> frequencyDiscriminate = frequencyWordService.getFrequencyDiscriminate(frequencyByParam,
				param, results);

		for (FrequencyWord frequencyWord : frequencyDiscriminate) {
			List<FrequencyWord> frequencyByParamRevert = frequencyWordService
					.getFrequencyByParam(frequencyWord.getWord(), results);

			boolean containsParam = false;
			for (FrequencyWord frequencyWord2 : frequencyByParamRevert) {
				if (frequencyWord2.getWord().equals(param)) {
					containsParam = true;
					break;
				}
			}
			assertTrue("The results have to contain the main param in a search with: " + frequencyWord + "\n"
					+ frequencyByParamRevert.toString(), containsParam);
		}

	}
}
