package nz.ac.auckland.unit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestKeywordExtraction {
	
	private KeywordService _keywordService;

	@Before
	public void setUp() throws Exception {
		_keywordService = new KeywordService();
	}

	@Test
	public void shouldSuccessfullyRemoveKeywordPresentInKeywordList() {
		// Given
		_keywordService.extractFrom("A dog walking service in Ponsonby");
		List<Keyword> originalExtractedKeywords = _keywordService.getKeywords();
		
		// When
		_keywordService.removeKeyword("Ponsonby");
		List<Keyword> extractedKeywordsAfterDeletion = _keywordService.getKeywords();
		
		// Then
		assertTrue(originalExtractedKeywords.size() > extractedKeywordsAfterDeletion.size());
		assertFalse(extractedKeywordsAfterDeletion.contains("Ponsonby"));
	}

}
