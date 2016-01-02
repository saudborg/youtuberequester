package nowdiscover.youtuberequester.executable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchResult;

import nowdiscover.youtuberequester.util.RandonNumberGeneration;
import nowdiscover.youtuberequester.youtube.Search;

public class AppIteraction {
	
	private static long NUMBER_OF_VIDEOS_RETURNED = 1;

	/*
	public static void main(String[] args) throws IOException {
		try {
			Search s = new Search();
			String query = getInputQuery();
			List<SearchResult> searchInYoutube = s.searchInYoutube(query, 1);
			
			if (searchInYoutube != null) {
				prettyPrint(searchInYoutube.iterator(), query);
			}
		} catch (GoogleJsonResponseException e) {
			System.err.println(
					"There was a service error: " + e.getDetails().getCode() + " : " + e.getDetails().getMessage());
		} catch (IOException e) {
			System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
		} catch (Throwable t) {
			t.printStackTrace();
		}
		
	}
	*/

	/*
	 * Prompt the user to enter a query term and return the user-specified term.
	 */
	@SuppressWarnings("unused")
	private static String getInputQuery() throws IOException {

		String inputQuery = "";

		System.out.print("Please enter a search term: ");
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		inputQuery = bReader.readLine();

		if (inputQuery.length() < 1) {
			// Use the string "YouTube Developers Live" as a default.
			inputQuery = "YouTube Developers Live";
		}
		return inputQuery;
	}

	/*
	 * Prints out all results in the Iterator. For each result, print the title,
	 * video ID, and thumbnail.
	 *
	 * @param iteratorSearchResults Iterator of SearchResults to print
	 *
	 * @param query Search query (String)
	 */
	@SuppressWarnings("unused")
	private static void prettyPrint(Iterator<SearchResult> iteratorSearchResults, String query) {

		System.out.println("\n=============================================================");
		System.out.println("   First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + query + "\".");
		System.out.println("=============================================================\n");

		if (!iteratorSearchResults.hasNext()) {
			System.out.println(" There aren't any results for your query.");
		}

		while (iteratorSearchResults.hasNext()) {

			SearchResult singleVideo = iteratorSearchResults.next();
			ResourceId rId = singleVideo.getId();

			// Confirm that the result represents a video. Otherwise, the
			// item will not contain a video ID.
			if (rId.getKind().equals("youtube#video")) {

				System.out.println(" Title: " + singleVideo.getSnippet().getTitle());
				System.out.println(" Description: " + singleVideo.getSnippet().getDescription());
				System.out.println(" URL: " + singleVideo.getId().getVideoId());
				System.out.println("\n-------------------------------------------------------------\n");
			}
		}
	}

}
