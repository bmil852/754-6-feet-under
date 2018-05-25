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
		if (word.equals("")) {
			throw new EmptyInputException();
		}
		
		boolean keywordDoesNotExist = true;
		for (int i = 0; i < this._keywords.size(); i++) {
			Keyword keyword = this._keywords.get(i);
			if (keyword.word.equals(word)) {
				_keywords.remove(keyword);
				keywordDoesNotExist = false;
			}
		}
		
		if (keywordDoesNotExist) {
			throw new NonExistingKeywordException();
		}
	}

	public void addKeyword(String word) {
		if (word.equals("")) {
			throw new EmptyInputException();
		}
		
		for (int i = 0; i < this._keywords.size(); i++) {
			Keyword keyword = this._keywords.get(i);
			if (keyword.word.equals(word)) {
				throw new AlreadyExistingKeywordException();
			}
		}
		
		Keyword keyword = new Keyword(word);
		_keywords.add(keyword);
	}

}
