package nowdiscover.youtuberequester.util;

import java.util.Comparator;
import java.util.Map;

public class ValueComparator implements Comparator<String> {
    Map<String, Integer> base;

    public ValueComparator(Map<String, Integer> base) {
        this.base = base;
    }


	@Override
	public int compare(String o1, String o2) {
		if (base.get(o1) >= base.get(o2)) {
            return -1;
        } else {
            return 1;
        } 
	}
}
