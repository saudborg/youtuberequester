package nowdiscover.youtuberequester;

import static org.junit.Assert.assertSame;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.google.api.services.youtube.model.SearchResult;

import nowdiscover.youtuberequester.youtube.Search;

/**
 * This class test if the result size is the same than expeteced
 * @author sauloborges
 *
 */
public class SearchTest {

	@Test
	public void testSizes1() throws IOException {
		List<SearchResult> searchInYoutube = Search.searchInYoutube("dog", 1);
		assertSame("Size result is different", searchInYoutube.size(), 1);
	}
	
	@Test
	public void testSizes50() throws IOException {
		List<SearchResult> searchInYoutube = Search.searchInYoutube("dog", 50);
		assertSame("Size result is different", searchInYoutube.size(), 50);
	}
	
	@Test
	public void testSizes100() throws IOException {
		List<SearchResult> searchInYoutube = Search.searchInYoutube("dog", 100);
		assertSame("Size result is different", searchInYoutube.size(), 100);
	}
	
	@Test
	public void testSizes0() throws IOException {
		List<SearchResult> searchInYoutube = Search.searchInYoutube("dog", 0);
		assertSame("Size result is different", searchInYoutube.size(), 0);
	}




}
