package nz.ac.auckland.unit;

import nz.ac.auckland.AlreadyExistingKeywordException;
import nz.ac.auckland.EmptyInputException;
import nz.ac.auckland.InsufficientInformationException;
import nz.ac.auckland.Keyword;
import nz.ac.auckland.KeywordExtractor;
import nz.ac.auckland.KeywordService;
import nz.ac.auckland.NonExistingKeywordException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestKeywordExtraction {
	
	private KeywordService _keywordService;

	@Before
	public void setUp() throws Exception {
		List<Keyword> extractedKeywords = new ArrayList<Keyword>();
		extractedKeywords.add(new Keyword("dog"));
		extractedKeywords.add(new Keyword("walking"));
		extractedKeywords.add(new Keyword("Ponsonby"));
		
		KeywordExtractor _keywordExtractor = mock(KeywordExtractor.class);
	    when(_keywordExtractor.extractFrom("A dog walking service in Ponsonby")).thenReturn(extractedKeywords);
	    when(_keywordExtractor.extractFrom("foo")).thenReturn(new ArrayList<Keyword>());
		
		this._keywordService = new KeywordService(_keywordExtractor);
	}

	@Test
	public void shouldSuccessfullyRemoveKeywordPresentInKeywordList() {
		// Given
		this._keywordService.extractFrom("A dog walking service in Ponsonby");
		List<Keyword> originalExtractedKeywords = this._keywordService.getKeywords();
		int originalExtractedKeywordsSize = originalExtractedKeywords.size();
		
		// When
		this._keywordService.removeKeyword("Ponsonby");
		List<Keyword> extractedKeywordsAfterDeletion = this._keywordService.getKeywords();
		int extractedKeywordsSizeAfterDeletion = extractedKeywordsAfterDeletion.size();
		
		// Then
		assertTrue(originalExtractedKeywordsSize > extractedKeywordsSizeAfterDeletion);
		
		Keyword keyword = new Keyword("Ponsonby");
		assertFalse(extractedKeywordsAfterDeletion.contains(keyword));
	}
	
	@Test
	public void shouldSuccessfullyAddKeywordNotAlreadyPresentInKeywordList() {
		// Given
		this._keywordService.extractFrom("A dog walking service in Ponsonby");
		List<Keyword> originalExtractedKeywords = this._keywordService.getKeywords();
		int originalExtractedKeywordsSize = originalExtractedKeywords.size();
		
		// When
		this._keywordService.addKeyword("cat");
		List<Keyword> keywordsAfterAddition = this._keywordService.getKeywords();
		int keywordsSizeAfterAddition = keywordsAfterAddition.size();
		
		// Then
		assertTrue(keywordsSizeAfterAddition > originalExtractedKeywordsSize);
		
		boolean foundAddedKeyword = false;
		for (Keyword keyword : keywordsAfterAddition) {
			if (keyword.word.equals("cat")) {
				foundAddedKeyword = true;
			}
		}
		assertTrue(foundAddedKeyword);
	}

	@Test(expected = NonExistingKeywordException.class)
	public void shouldNotAllowRemovalOfNonExistingKeyword() {
		// Given
		this._keywordService.extractFrom("A dog walking service in Ponsonby");
		
		// When
		this._keywordService.removeKeyword("cat");
	}
	
	@Test(expected = AlreadyExistingKeywordException.class)
	public void shouldNotAllowAdditionOfAlreadyExistingKeyword() {
		// Given
		this._keywordService.extractFrom("A dog walking service in Ponsonby");
		
		// When
		this._keywordService.addKeyword("dog");
	}
	
	@Test(expected = EmptyInputException.class)
	public void shouldNotAddEmptyKeywordString() {
		// Given
		this._keywordService.extractFrom("A dog walking service in Ponsonby");
		
		// When
		this._keywordService.addKeyword("");
	}
	
	@Test(expected = EmptyInputException.class)
	public void shouldNotRemoveEmptyKeywordString() {
		// Given
		this._keywordService.extractFrom("A dog walking service in Ponsonby");
		
		// When
		this._keywordService.removeKeyword("");
	}
	
	@Test
	public void shouldReturnNonEmptyKeywordsForBusinessIdeaInput() {
		// Given
		this._keywordService.extractFrom("A dog walking service in Ponsonby");
		
		// When
		List<Keyword> extractedKeywords = this._keywordService.getKeywords();
		
		// Then
		assertTrue(extractedKeywords.size() > 0);
		
		boolean foundEmptyKeyword = false;
		for (Keyword keyword : extractedKeywords) {
			if (keyword.word.equals("")) {
				foundEmptyKeyword = true;
			}
		}
		assertFalse(foundEmptyKeyword);
	}
	
	@Test(expected = InsufficientInformationException.class)
	public void shouldReturnNoKeywordsForInsufficientBusinessIdeaInput() {
		// When
		this._keywordService.extractFrom("foo");
	}
}
