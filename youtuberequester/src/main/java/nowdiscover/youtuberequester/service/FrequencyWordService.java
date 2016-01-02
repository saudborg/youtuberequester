package nowdiscover.youtuberequester.service;

import java.io.IOException;
import java.util.List;

import nowdiscover.youtuberequester.domain.FrequencyWord;

public interface FrequencyWordService {
	
	public List<FrequencyWord> getFrequencyByParam(String param, Integer maximumNumber) throws IOException;
	
	public List<FrequencyWord> getFrequencyDiscriminate(List<FrequencyWord> list, String param, Integer maxNumber) throws IOException;
	
	public List<FrequencyWord> getDiscriminateWords(String param, Integer maxNumber) throws IOException;


}
