package nz.ac.auckland;

import java.util.ArrayList;
import java.util.List;

public class KeywordService {
	
	private List<Keyword> _keywords;

	public List<Keyword> getKeywords() {
		return _keywords;
	}

	public void extractFrom(String businessIdea) {
		Keyword _keyword = new Keyword("foo");
		
		_keywords = new ArrayList<Keyword>();
		_keywords.add(_keyword);
	}

	public void removeKeyword(String keyword) {
		_keywords = new ArrayList<Keyword>();
	}

}
