package nowdiscover.youtuberequester.util;

public class RandonNumberGeneration {
	
	public static Integer getARandonNumberLower50() {
		int max = 50;
		int min = 0;

		int randomNum = min + (int) (Math.random() * ((max - min) + 1));

		return randomNum;
	}
	
	public static Integer getARandonNumberBigger50() {

		int max = 500;
		int min = 50;

		int randomNum = min + (int) (Math.random() * ((max - min) + 1));

		return randomNum;
	}

}
