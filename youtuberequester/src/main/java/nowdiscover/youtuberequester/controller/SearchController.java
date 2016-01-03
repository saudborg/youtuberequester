package nowdiscover.youtuberequester.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;

import nowdiscover.youtuberequester.domain.FrequencyWord;
import nowdiscover.youtuberequester.service.FrequencyWordService;
import nowdiscover.youtuberequester.util.RandonNumberGeneration;

@Controller
public class SearchController {

	private static final String SEARCH_PAGE = "best-results";
	private static final String HOME_PAGE = "welcome";

	@Autowired
	private FrequencyWordService frequencyWordService;

	/**
	 * This end point is responsible for searching in Youtube the frequency of
	 * each word appear when you pass a param
	 * 
	 * @param map
	 * @param query
	 * @param maxNumber
	 * @return
	 */
	@RequestMapping("/home")
	public String home(ModelMap map, @RequestParam(required = false) String query,
			@RequestParam(required = false) Integer maxNumber) {

		// Default searching
		if (query == null) {
			query = "duck";
		}
		map.put("param", query);

		// If don't pass anything, the system will generate a number > 50
		if (maxNumber == null) {
			maxNumber = RandonNumberGeneration.getARandonNumberBigger50();
		}
		map.put("maxNumber", maxNumber);

		try {
			List<FrequencyWord> frequencyByParam = frequencyWordService.getFrequencyByParam(query, maxNumber);

			if (frequencyByParam != null) {
				map.put("result", frequencyByParam);
			}

		} catch (GoogleJsonResponseException e) {
			System.err.println(
					"There was a service error: " + e.getDetails().getCode() + " : " + e.getDetails().getMessage());
			map.put("message",
					"There was a service error: " + e.getDetails().getCode() + " : " + e.getDetails().getMessage());
		} catch (IOException e) {
			System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
			map.put("message", "There was an IO error: " + e.getCause() + " : " + e.getMessage());
		} catch (Throwable t) {
			t.printStackTrace();
		}

		return HOME_PAGE;
	}

	/**
	 * This end point is responsible for searching in youtube all the words that
	 * appear in a query, and search this words to see how many times appear the
	 * query in this results
	 * 
	 * @param map
	 * @param query
	 * @param maxNumber
	 * @return
	 */
	@RequestMapping("/search")
	public String search(ModelMap map, @RequestParam(required = false) String query,
			@RequestParam(required = false) Integer maxNumber) {

		// Default searching
		if (query == null) {
			query = "duck";
		}
		map.put("param", query);

		// If don't pass anything, the system will generate a number > 50
		if (maxNumber == null) {
			maxNumber = RandonNumberGeneration.getARandonNumberBigger50();
		}
		map.put("maxNumber", maxNumber);

		try {

			List<FrequencyWord> frequencyDiscriminate = frequencyWordService.getDiscriminateWords(query, maxNumber);
			if (frequencyDiscriminate != null) {
				map.put("bestResults", frequencyDiscriminate);
			}

		} catch (GoogleJsonResponseException e) {
			map.put("message",
					"There was a service error: " + e.getDetails().getCode() + " : " + e.getDetails().getMessage());
			System.err.println(
					"There was a service error: " + e.getDetails().getCode() + " : " + e.getDetails().getMessage());
		} catch (IOException e) {
			map.put("message", "There was an IO error: " + e.getCause() + " : " + e.getMessage());
			System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
		} catch (Throwable t) {
			t.printStackTrace();
		}

		return SEARCH_PAGE;
	}

}
