package nz.ac.auckland.acceptance;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nz.ac.auckland.Login;


public class AccountManagementStepDefs {
    

    @Given("^a new (?:User|Administrator) wants to sign up to the system$")
    public void a_new_User_or_Administrator_wants_to_sign_up_to_the_system() {

    }

    @When("^the (User|Administrator) provides valid details for signing up$")
    public void the_User_or_Administrator_provides_valid_details_for_signing_up(String roleType) {

    }

    @Then("^the (User|Administrator) is succesfully registered with the system$")
    public void the_User_or_Administrator_is_succesfully_registered_with_the_system(String roleType) {

    }




}
