package nz.ac.auckland.acceptance;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class TravisStepDefs {
	@Given("^Nothing has happened$")
	public void nothing_has_happened() throws Exception {
	    //Nothing Happens
	}

	@When("^I do nothing$")
	public void i_do_nothing() throws Exception {
	    //Do Nothing
	}

	@Then("^Asserting true should work$")
	public void asserting_true_should_work() throws Exception {
	    assertThat(true, equalTo(true));
	}

}