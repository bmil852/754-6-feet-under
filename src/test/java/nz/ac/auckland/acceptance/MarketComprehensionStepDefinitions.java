package nz.ac.auckland.acceptance;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class MarketComprehensionStepDefinitions {
    List keywords<Keyword>;

    @Given("^user has a set of weighted keywords to search for$")
    public void User_has_a_set_of_weighted_keywords_to_search_for() {
       Keyword keyword1;
       Keyword keyword2;
       keyword1.setWeight(1);
       keyword2.setWeight(2);
       keywords.add(keyword1);
       keywords.add(keyword2);
    }

    @When("^user performs a search$")
    public void User_performs_a_search() {
        List<Object> results = searchAndProcess(keywords);
    }

    @Then("^a non-empty set of documents are returned$")
    public void non_empty_set_of_documents_are_returned() {
        assertThat(results instanceof Document, equalTo(true));
        assertThat(results.size() > 0, equalTo(true));
    }
}
