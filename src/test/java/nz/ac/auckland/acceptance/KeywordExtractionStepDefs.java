package nz.ac.auckland.acceptance;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nz.ac.auckland.Keyword;
import nz.ac.auckland.KeywordExtractor;
import nz.ac.auckland.KeywordService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

public class KeywordExtractionStepDefs {

	private KeywordService _keywordService;

	@Given("^a User already has a number of keywords returned$")
	public void a_User_already_has_a_number_of_keywords_returned() throws Exception {
		initializeKeywordServiceWithMockKeywordExtractor();
		this._keywordService.extractFrom("A dog walking service in Ponsonby");
	}

	@When("^the User requests to change the weight of keyword \"(.*)\" to (\\d+)$")
	public void the_User_requests_to_change_the_weight_of_keyword_to(String word, int newWeight) throws Exception {
		this._keywordService.updateWeight(word, newWeight);
	}

	@Then("^the weight of keyword \"(.*)\" is updated to (\\d+)$")
	public void the_weight_of_keyword_is_updated_to(String word, int newWeight) throws Exception {
		List<Keyword> extractedKeywords = this._keywordService.getKeywords();
		for (int i = 0; i < extractedKeywords.size(); i++) {
			Keyword keyword = extractedKeywords.get(i);
			if (keyword.word.equals(word)) {
				assertEquals(keyword.getWeight(), newWeight);
			}
		}
	}
	
	private void initializeKeywordServiceWithMockKeywordExtractor() {
		List<Keyword> extractedKeywords = new ArrayList<Keyword>();
		extractedKeywords.add(new Keyword("dog"));
		extractedKeywords.add(new Keyword("walking"));
		extractedKeywords.add(new Keyword("Ponsonby"));

		KeywordExtractor _keywordExtractor = mock(KeywordExtractor.class);
		when(_keywordExtractor.extractFrom("A dog walking service in Ponsonby")).thenReturn(extractedKeywords);
		this._keywordService = new KeywordService(_keywordExtractor);
	}
}
