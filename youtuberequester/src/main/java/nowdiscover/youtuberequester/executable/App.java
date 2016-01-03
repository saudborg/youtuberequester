package nowdiscover.youtuberequester.executable;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.util.ArrayMap;
import com.google.api.services.youtube.model.SearchResult;

import nowdiscover.youtuberequester.util.MapUtils;
import nowdiscover.youtuberequester.util.RandonNumberGeneration;
import nowdiscover.youtuberequester.youtube.Search;

public class App {

	/*
	public static void main(String[] args) {
		try {

			Integer resultsNumber = RandonNumberGeneration.getARandonNumberBigger50();

			String querySearch = "duck";
			List<SearchResult> duckResults = Search.searchInYoutube(querySearch, resultsNumber);
			Map<String, Integer> mapDuckDuck = MapUtils.fillMapWords(duckResults.iterator());
			TreeMap<String, Integer> sortedMapDucks = MapUtils.sortMap(mapDuckDuck);
			printMap(resultsNumber, mapDuckDuck, sortedMapDucks, querySearch);

			Map<String, Integer> wordResults = new ArrayMap<String, Integer>();
			for (int i = 1; i <= 11; i++) {
				String word = (String) sortedMapDucks.keySet().toArray()[i];
				List<SearchResult> results = Search.searchInYoutube(word, resultsNumber);
				Map<String, Integer> map = MapUtils.fillMapWords(results.iterator());

				Integer integer = map.get(querySearch);
				wordResults.put(word, integer);
			}

			Set<String> keySet = wordResults.keySet();
			System.out.println("\n=============================================================");
			System.out.println("Results for " + "duck");
			System.out.println("=============================================================");
			for (String string : keySet) {
				System.out.println("Word: " + string + " | " + wordResults.get(string));
			}

		} catch (GoogleJsonResponseException e) {
			System.err.println(
					"There was a service error: " + e.getDetails().getCode() + " : " + e.getDetails().getMessage());
		} catch (IOException e) {
			System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
		} catch (Throwable t) {
			t.printStackTrace();
		}

	}*/

	private static void printMap(Integer resultsNumber, Map<String, Integer> mapWords,
			TreeMap<String, Integer> sorted_map, String querySearch) {
		Set<String> keySet = sorted_map.keySet();

		int totalWords = 0;
		for (String string : keySet) {
			totalWords = totalWords + mapWords.get(string);
		}

		System.out.println("\n=============================================================");
		System.out.println("   First " + resultsNumber + " videos for search on \"" + querySearch
				+ "\". (Total words found: " + totalWords + ")");
		System.out.println("=============================================================\n");
		for (String key : keySet) {
			double freq = (mapWords.get(key) * 100) / totalWords;
			System.out.println("Word: " + key + " | Value: " + mapWords.get(key) + " | Frequencie: " + freq + "%");
		}
	}

}
