package nowdiscover.youtuberequester.youtube;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import nowdiscover.youtuberequester.util.Auth;

/**
 * Class copied from Google Developers and changed to use as I need
 *
 */
public class Search {

	/**
	 * Define a global variable that identifies the name of a file that contains
	 * the developer's API key.
	 */
	private static final String PROPERTIES_FILENAME = "youtube.properties";

	/**
	 * Define a global instance of a Youtube object, which will be used to make
	 * YouTube Data API requests.
	 */
	private static YouTube youtube;

	public static List<SearchResult> searchInYoutube(String query, Integer maximumNumber) throws IOException {
		Properties properties = new Properties();

		try {
			InputStream in = Search.class.getResourceAsStream("/" + PROPERTIES_FILENAME);
			properties.load(in);
		} catch (IOException e) {
			System.err.println(
					"There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause() + " : " + e.getMessage());
			System.exit(1);
		}

		// This object is used to make YouTube Data API requests. The last
		// argument is required, but since we don't need anything
		// initialized when the HttpRequest is initialized, we override
		// the interface and provide a no-op function.
		youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer() {
			public void initialize(HttpRequest request) throws IOException {
			}
		}).setApplicationName("youtube-cmdline-search-sample").build();

		// Define the API request for retrieving search results.
		YouTube.Search.List search = youtube.search().list("id,snippet");

		// Set your developer key from the Google Developers Console for
		// non-authenticated requests. See:
		// https://console.developers.google.com/
		String apiKey = properties.getProperty("youtube.apikey");
		search.setKey(apiKey);
		search.setQ(query);

		// Restrict the search results to only include videos. See:
		// https://developers.google.com/youtube/v3/docs/search/list#type
		search.setType("video");

		// To increase efficiency, only retrieve the fields that the
		// application uses.
		search.setFields("items(id/kind,snippet/title,snippet/description,id/videoId)");
		
		// If the maximum number of request is bigger than 50, then would need more than 1 request
		List<SearchResult> searchResultList = new ArrayList<SearchResult>();
		long resultsLeft = (long) maximumNumber;
		String pageToken = null;
		while(resultsLeft > 50){
			search.setMaxResults(50l);
			if(pageToken != null){
				search.setPageToken(pageToken);
			}
			SearchListResponse searchResponse = search.execute();
			searchResultList.addAll(searchResponse.getItems());
			pageToken = searchResponse.getNextPageToken();
			resultsLeft = resultsLeft - 50;
		}
		search.setMaxResults(resultsLeft);
		search.setPageToken(pageToken);
		SearchListResponse searchResponse = search.execute();
		searchResultList.addAll(searchResponse.getItems());

		return searchResultList;

	}

}
