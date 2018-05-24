package nz.ac.auckland.acceptance;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nz.ac.auckland.Client;
import nz.ac.auckland.AccountManagementService;
import nz.ac.auckland.Role;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;


public class AccountManagementStepDefs {

    private AccountManagementService _accountManagementService;

    @Given("^a new (?:User|Administrator) wants to sign up to the system$")
    public void a_new_User_or_Administrator_wants_to_sign_up_to_the_system() {
        _accountManagementService = new AccountManagementService();
    }

    @When("^the (User|Administrator) provides (valid|invalid) details for signing up$")
    public void the_User_or_Administrator_provides_valid_or_invalid_details_for_signing_up(String clientType,String validity) {
        String username = (validity.equals("valid")) ? "JohnSmith1" : null;
        String password = (validity.equals("valid")) ? "pass1" : null;
        Client client = new Client(username, password);
        Role roleType = (clientType.equals("User")) ? Role.USER : Role.ADMINISTRATOR;
        _accountManagementService.register(client, roleType);
    }

    @Then("^the (User|Administrator) (is|is not) successfully registered with the system$")
    public void the_User_or_Administrator_is_or_is_not_successfully_registered_with_the_system(String clientType, String success) {
        boolean registered = (success.equals("is"));
        Role roleType = (clientType.equals("User")) ? Role.USER : Role.ADMINISTRATOR;
        assertThat(_accountManagementService.checkClientRegistered("JohnSmith1","pass1", roleType), equalTo(registered));
    }

    @Given("^(a User|an Administrator) is already signed into the system$")
    public void a_User_or_Administrator_is_already_signed_into_the_system(String clientType) {
        _accountManagementService = new AccountManagementService();
        Role roleType = (clientType.contains("User")) ? Role.USER : Role.ADMINISTRATOR;
        Client client = new Client("UserTest1", "UserTestPass1");
        _accountManagementService.register(client, roleType);
        _accountManagementService.signIn("UserTest1", "UserTestPass1", roleType);
    }

    @When("^the (User|Administrator) signs out of the system$")
    public void the_User_or_Administrator_signs_out_of_the_system(String clientType) {
        Role roleType = (clientType.contains("User")) ? Role.USER : Role.ADMINISTRATOR;
        Client client = new Client("UserTest1", "UserTestPass1");
        _accountManagementService.signOut(client, roleType);
    }

    @Then("^the (User|Administrator) is no longer signed in to the system$")
    public void the_User_or_Administrator_is_no_longer_signed_in_to_the_system(String clientType) {
        Role roleType = (clientType.contains("User")) ? Role.USER : Role.ADMINISTRATOR;
        assertFalse(_accountManagementService.checkClientActive("UserTest1", "UserTestPass1",roleType));
    }
}
