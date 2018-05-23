package nz.ac.auckland.acceptance;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nz.ac.auckland.Client;
import nz.ac.auckland.Login;
import nz.ac.auckland.Role;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;


public class AccountManagementStepDefs {

    private Login _login;

    @Given("^a new (?:User|Administrator) wants to sign up to the system$")
    public void a_new_User_or_Administrator_wants_to_sign_up_to_the_system() {
        _login = new Login();
    }

    @When("^the (User|Administrator) provides valid details for signing up$")
    public void the_User_or_Administrator_provides_valid_details_for_signing_up(String clientType) {
        String username = "JohnSmith1";
        String password = "pass1";
        Client client = new Client(username, password);
        Role roleType = (clientType.equals("User")) ? Role.USER : Role.ADMINISTRATOR;
        _login.register(client, roleType);
    }

    @Then("^the (User|Administrator) is succesfully registered with the system$")
    public void the_User_or_Administrator_is_succesfully_registered_with_the_system(String clientType) {
        Role roleType = (clientType.equals("User")) ? Role.USER : Role.ADMINISTRATOR;
        assertThat(_login.getRegistered(roleType).contains("JohnSmith1"), equalTo(true));
    }




}
