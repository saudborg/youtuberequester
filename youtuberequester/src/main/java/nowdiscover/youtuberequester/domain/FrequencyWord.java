package nowdiscover.youtuberequester.domain;

import java.math.BigDecimal;

/**
 * Class that represents a word and how many times appear in one request to
 * Youtube
 * 
 * @author sauloborges
 *
 */
public class FrequencyWord implements Comparable<FrequencyWord> {

	private String word;
	private Integer count;
	private BigDecimal frequency;

	public FrequencyWord(String word, Integer count) {
		super();
		this.word = word;
		this.count = count;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public int compareTo(FrequencyWord o) {
		if (this.getCount() > o.getCount()) {
			return -1;
		} else if (this.getCount() < o.getCount()){
			return 1;
		} else
			return 0;
	}

	@Override
	public String toString() {
		return "FrequencyWord [word=" + word + ", count=" + count + "]";
	}

	public BigDecimal getFrequency() {
		return frequency;
	}

	public void setFrequency(BigDecimal frequencie) {
		this.frequency = frequencie;
	}

}
