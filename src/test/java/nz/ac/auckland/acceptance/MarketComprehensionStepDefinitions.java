package nz.ac.auckland.acceptance;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nz.ac.auckland.APICommunicator;
import nz.ac.auckland.Document;
import nz.ac.auckland.Keyword;
import nz.ac.auckland.SearchEngine;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class MarketComprehensionStepDefinitions {
    List<Keyword> keywords = new ArrayList<>();
    List<? extends Object> results;
    SearchEngine _searchEngineAlgorithm;

    @Given("^user has a set of weighted keywords to search with$")
    public void User_has_a_set_of_weighted_keywords_to_search_for() {
       Keyword keyword1 = new Keyword("first");
       Keyword keyword2 = new Keyword("second");
       keyword1.setWeight(1);
       keyword2.setWeight(2);
       keywords.add(keyword1);
       keywords.add(keyword2);
    }

    @When("^user performs a search$")
    public void User_performs_a_search() {
            mock_search_documents_to_be_returned_after_performing_search();
            _searchEngineAlgorithm.searchAndProcess(keywords);
            results = _searchEngineAlgorithm.getSearchResults();
    }

    @When("^the user performs a search$")
    @Then("^failure is expected as it must return some documents$")
    public void User_performs_a_different_search() {
        try {
            mock_search_when_no_documents_are_returned_after_performing_search();
            _searchEngineAlgorithm.searchAndProcess(keywords);
            results = _searchEngineAlgorithm.getSearchResults();
            fail("Should throw a runtime exception");
        }catch(RuntimeException e){
            assertEquals("Search must return a set of documents",e.getMessage());
        }
    }

    @Then("^a non-empty set of documents are returned$")
    public void non_empty_set_of_documents_are_returned() {
        for(Object d : results) {
            assertThat(d instanceof Document, equalTo(true));
        }
        assertThat(results.size() > 1, equalTo(true));
    }

    private void mock_search_documents_to_be_returned_after_performing_search(){
        APICommunicator apiCommunicator = mock(APICommunicator.class);
        List<Document> documents = new ArrayList<>();
        Document d1 = new Document("First mock text for a document object");
        Document d2 = new Document("Second mock text for a document object");
        documents.add(d1);
        documents.add(d2);
        when(apiCommunicator.search(anyList())).thenReturn(documents);
        _searchEngineAlgorithm = new SearchEngine(apiCommunicator);
    }

    private void mock_search_when_no_documents_are_returned_after_performing_search(){
        APICommunicator apiCommunicator = mock(APICommunicator.class);
        List<Document> documents = new ArrayList<>();
        when(apiCommunicator.search(anyList())).thenReturn(documents);
        _searchEngineAlgorithm = new SearchEngine(apiCommunicator);
    }
}
