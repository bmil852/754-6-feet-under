package nz.ac.auckland;

import java.util.List;

public class KeywordService {
	
	private List<Keyword> _keywords;
	private KeywordExtractor _keywordExtractor;

	public KeywordService(KeywordExtractor keywordExtractor) {
		this._keywordExtractor = keywordExtractor;
	}

	public List<Keyword> getKeywords() {
		return _keywords;
	}

	public void extractFrom(String businessIdea) {
		_keywords = this._keywordExtractor.extractFrom(businessIdea);
	}

	public void removeKeyword(String word) {
		for (int i = 0; i < this._keywords.size(); i++) {
			Keyword keyword = this._keywords.get(i);
			if (keyword.word.equals(word)) {
				_keywords.remove(keyword);
			}
		}
	}

}
