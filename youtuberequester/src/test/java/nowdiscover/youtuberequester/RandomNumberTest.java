package nowdiscover.youtuberequester;

import static junit.framework.Assert.*;

import org.junit.Test;

import nowdiscover.youtuberequester.util.RandonNumberGeneration;

/**
 * Class to test if the class RandonNumberGeneration always get a number bigger
 * then 50 or lass then 50 when you choose wich one you wanna
 * 
 * @author sauloborges
 *
 */
public class RandomNumberTest {

	/**
	 * This test get a number > 50
	 */
	@Test
	public void testBiggerThan50() {
		for (int i = 0; i < 10; i++) {
			Integer aRandonNumberBigger50 = RandonNumberGeneration.getARandonNumberBigger50();
			assertTrue("The number generetade is under", aRandonNumberBigger50 > 50);
		}
	}

	/**
	 * This test get a number < 50
	 */
	@Test
	public void testUnderThan50() {
		for (int i = 0; i < 10; i++) {
			Integer aRandonNumberUnder50 = RandonNumberGeneration.getARandonNumberLower50();
			assertTrue("The number generetade is bellow", aRandonNumberUnder50 < 50);
		}
	}
}
