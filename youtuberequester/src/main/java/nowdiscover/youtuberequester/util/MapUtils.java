package nowdiscover.youtuberequester.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchResult;

/**
 * This class is responsible to sort a map and create a new map with attributes
 * from Youtube
 * 
 * @author sauloborges
 *
 */
public class MapUtils {

	public static TreeMap<String, Integer> sortMap(Map<String, Integer> mapWords) {
		ValueComparator bvc = new ValueComparator(mapWords);
		TreeMap<String, Integer> sorted_map = new TreeMap<String, Integer>(bvc);
		sorted_map.putAll(mapWords);
		return sorted_map;
	}

	/**
	 * Recieve a list of words and split counting how many times each one appear
	 * 
	 * @param iteratorSearchResults
	 * @return
	 */
	public static Map<String, Integer> fillMapWords(Iterator<SearchResult> iteratorSearchResults) {

		Map<String, Integer> mapFrequency = new HashMap<String, Integer>();

		if (!iteratorSearchResults.hasNext()) {
			System.out.println(" There aren't any results for your query.");
		}

		while (iteratorSearchResults.hasNext()) {

			SearchResult singleVideo = iteratorSearchResults.next();
			ResourceId rId = singleVideo.getId();

			// Confirm that the result represents a video. Otherwise, the
			// item will not contain a video ID.
			if (rId.getKind().equals("youtube#video")) {
				String title = singleVideo.getSnippet().getTitle().toLowerCase();

				// Normalize it to doesn't has difference between upper case and
				// lower case. As the same to doesn't cosidering punctuation
				String regex = "([^a-zA-Z']+)'*\\1*";
				String[] split = title.split(regex);

				for (String word : split) {
					Integer count = 1;
					if (mapFrequency.containsKey(word)) {
						count = mapFrequency.get(word);
						count++;
					}
					mapFrequency.put(word, count);
				}
			}
		}
		return mapFrequency;

	}
}
