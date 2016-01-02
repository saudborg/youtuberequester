package nowdiscover.youtuberequester.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.google.api.client.util.ArrayMap;
import com.google.api.services.youtube.model.SearchResult;

import nowdiscover.youtuberequester.domain.FrequencyWord;
import nowdiscover.youtuberequester.util.MapUtils;
import nowdiscover.youtuberequester.youtube.Search;

@Service
public class FrequencyWordServiceImpl implements FrequencyWordService {

	/**
	 * This metod is responsible for:
	 * Search in Youtube API all the words that appear in results when use a query parameter
	 * 
	 * Return a list of words if isn't empty
	 */
	public List<FrequencyWord> getFrequencyByParam(String param, Integer maximumNumber) throws IOException {
		List<SearchResult> result = Search.searchInYoutube(param, maximumNumber);
		Map<String, Integer> mapResult = MapUtils.fillMapWords(result.iterator());

		List<FrequencyWord> frequencyList = transform(mapResult);
		if (!frequencyList.isEmpty()){
			Collections.sort(frequencyList);
			calculateFrequency(frequencyList);
		}
		return frequencyList;
	}

	/**
	 * Recieve a map with results and transform in a List of an object FrequencyWord
	 * @param mapResult
	 */
	private List<FrequencyWord> transform(Map<String, Integer> mapResult) {
		List<FrequencyWord> frequencyList = new ArrayList<FrequencyWord>();
		
		Set<String> keySet = mapResult.keySet();
		for (String word : keySet) {
			if (mapResult.get(word) != null && mapResult.get(word) >= 0)
				frequencyList.add(new FrequencyWord(word, mapResult.get(word)));
		}
		return frequencyList;
	}

	/**
	 * Search in a map of words, how many times each one appear
	 */
	public List<FrequencyWord> getFrequencyDiscriminate(List<FrequencyWord> list, String param, Integer maxNumber)
			throws IOException {

		Map<String, Integer> wordResults = new ArrayMap<String, Integer>();
		for (int i = 1; i < 10; i++) {
			String word = list.get(i).getWord();
			List<SearchResult> results = Search.searchInYoutube(word, maxNumber);
			Map<String, Integer> map = MapUtils.fillMapWords(results.iterator());

			Integer integer = map.get(param);
			wordResults.put(word, integer);
		}
		List<FrequencyWord> frequencyList = transform(wordResults);
		if (!frequencyList.isEmpty())
			Collections.sort(frequencyList);
		return frequencyList;
	}

	@Override
	public List<FrequencyWord> getDiscriminateWords(String param, Integer maxNumber) throws IOException {
		List<FrequencyWord> list = this.getFrequencyByParam(param, maxNumber);
		List<FrequencyWord> frequencyDiscriminate = this.getFrequencyDiscriminate(list, param, maxNumber);
		return frequencyDiscriminate;
		
	}
	
	private void calculateFrequency(List<FrequencyWord> list){
		int totalWords = 0;
		for (FrequencyWord frequencyWord : list) {
			totalWords = totalWords + frequencyWord.getCount();
		}
		
		for (FrequencyWord frequencyWord : list) {
			double freq = ((frequencyWord.getCount() * 100) / totalWords);
			BigDecimal b = new BigDecimal(freq);
			frequencyWord.setFrequency(b);
		}
	}

}
