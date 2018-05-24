package nz.ac.auckland.unit;

import nz.ac.auckland.Client;
import nz.ac.auckland.AccountManagementService;
import nz.ac.auckland.Role;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

public class TestAccountManagement {
    private AccountManagementService _accountManagementService;

    @Test
    public void when_user_wants_to_sign_in() {
        //Given
        Client client = new Client("User1", "Password1");
        _accountManagementService = new AccountManagementService();
        _accountManagementService.register(client, Role.USER);

        //When
        _accountManagementService.signIn("User1", "Password1", Role.USER);
        boolean validSignIn = _accountManagementService.checkClientSignedIn(client, Role.USER);

        //Then
        assertEquals(true, validSignIn);
    }

    @Test
    public void when_administrator_wants_to_sign_in() {
        //Given
        Client client = new Client("Administrator1", "PasswordAdmin1");
        _accountManagementService = new AccountManagementService();
        _accountManagementService.register(client, Role.ADMINISTRATOR);

        //When
        _accountManagementService.signIn("Administrator1", "PasswordAdmin1", Role.ADMINISTRATOR);
        boolean validSignIn = _accountManagementService.checkClientSignedIn(client, Role.ADMINISTRATOR);

        //Then
        assertEquals(true, validSignIn);
    }

    @Test(expected=RuntimeException.class)
    public void when_user_wants_to_sign_in_with_invalid_credentials() {
        //Given
        Client client = new Client("User1", "Password1");
        _accountManagementService = new AccountManagementService();
        _accountManagementService.register(client, Role.USER);

        //When
        _accountManagementService.signIn("WrongUser1", "WrongPassword1", Role.USER);
        _accountManagementService.checkClientSignedIn(client, Role.USER);

        //Then
        fail(Role.USER.exceptionMessage);
    }

    @Test(expected=RuntimeException.class)
    public void when_administrator_wants_to_sign_in_with_invalid_credentials() {
        //Given
        Client client = new Client("Admin1", "AdminPassword1");
        _accountManagementService = new AccountManagementService();
        _accountManagementService.register(client, Role.ADMINISTRATOR);

        //When
        _accountManagementService.signIn("WrongAdmin1", "WrongAdminPassword1", Role.ADMINISTRATOR);
        _accountManagementService.checkClientSignedIn(client, Role.ADMINISTRATOR);

        //Then
        fail(Role.ADMINISTRATOR.exceptionMessage);
    }

    @Test
    public void search_count_for_user_in_current_session(){
        //Given
        Client client = new Client("UserActiveSearchCount1", "UserActiveSearchCountPassword1");
        _accountManagementService = new AccountManagementService();
        _accountManagementService.register(client, Role.USER);
        _accountManagementService.signIn("UserActiveSearchCount1", "UserActiveSearchCountPassword1", Role.USER);

        //When
        for (int i = 0; i < 4; i++) {
            client.performSearch();
        }

        //Then
        assertEquals(4,client.getCurrentSessionSearchCount());
    }

    @Test
    public void active_search_count_for_user_after_sign_out(){
        //Given
        Client client = new Client("UserActiveSearchCountSignOut1", "UserActiveSearchCountSignOutPassword1");
        _accountManagementService = new AccountManagementService();
        _accountManagementService.register(client, Role.USER);
        _accountManagementService.signIn("UserActiveSearchCountSignOut1", "UserActiveSearchCountSignOutPassword1", Role.USER);

        //And client carries out searches before signing out
        for (int i = 0; i < 4; i++) {
            client.performSearch();
        }

        //When
        _accountManagementService.signOut(client, Role.USER);

        //Then
        assertEquals(0,client.getCurrentSessionSearchCount());
    }

    @Test
    public void total_search_count_for_user(){

        //Given
        Client client = new Client("UserTotalSearchCount1", "UserTotalSearchCountPassword1");
        _accountManagementService = new AccountManagementService();
        _accountManagementService.register(client, Role.USER);
        _accountManagementService.signIn("UserTotalSearchCount1", "UserTotalSearchCountPassword1", Role.USER);

        //And client carries out some searches while signed in
        for (int i = 0; i < 6; i++) {
            client.performSearch();
        }
        // Client signs out
        _accountManagementService.signOut(client, Role.USER);

        //When
        // Client signs back in
        _accountManagementService.signIn("UserTotalSearchCount1", "UserTotalSearchCountPassword1", Role.USER);

        // And carries out more searches
        for (int i = 0; i < 4; i++) {
            client.performSearch();
        }

        //Then
        assertEquals(10,client.getTotalSearchCount());
    }


    @Test
    public void administrator_obtains_total_number_of_registered_users(){
        _accountManagementService = new AccountManagementService();

        //Given
        // An administrator is registered
        Client admin = new Client("AdminObtainsRegisteredUserCount1", "AdminObtainsRegisteredUserCountPassword1");
        _accountManagementService.register(admin, Role.ADMINISTRATOR);
        _accountManagementService.signIn("AdminObtainsRegisteredUserCount1", "AdminObtainsRegisteredUserCountPassword1", Role.ADMINISTRATOR);

        // And a number of users are registered
        for(int i =0; i<5; i++) {
            Client user = new Client("RegisteredUser" + i, "RegisteredUserPassword" + i);
            _accountManagementService.register(user, Role.USER);
        }

        //When
        // Administrator wants to get number of registered users
        int numberOfRegisteredUsers = _accountManagementService.getNumberOfRegisteredUsers(Role.ADMINISTRATOR);

        //Then
        assertEquals(5,numberOfRegisteredUsers);
    }

    @Test
    public void administrator_obtains_zero_total_number_of_registered_users_when_no_users_are_registered(){
        _accountManagementService = new AccountManagementService();

        //Given
        // An administrator is registered and no users are registered
        Client admin = new Client("AdminObtainsRegisteredUserCount1", "AdminObtainsRegisteredUserCountPassword1");
        _accountManagementService.register(admin, Role.ADMINISTRATOR);
        _accountManagementService.signIn("AdminObtainsRegisteredUserCount1", "AdminObtainsRegisteredUserCountPassword1", Role.ADMINISTRATOR);

        //When
        // Administrator wants to get number of registered users
        int numberOfRegisteredUsers = _accountManagementService.getNumberOfRegisteredUsers(Role.ADMINISTRATOR);

        //Then
        assertEquals(0,numberOfRegisteredUsers);
    }
}
