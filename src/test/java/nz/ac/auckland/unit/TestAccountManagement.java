package nz.ac.auckland.unit;

import nz.ac.auckland.Client;
import nz.ac.auckland.Login;
import nz.ac.auckland.Role;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

public class TestAccountManagement {
    private Login _login;

    @Test
    public void when_user_wants_to_sign_in() {
        //Given
        Client client = new Client("User1", "Password1");
        _login = new Login();
        _login.register(client, Role.USER);

        //When
        _login.signIn("User1", "Password1", Role.USER);
        boolean validSignIn = _login.checkClientSignedIn(client, Role.USER);

        //Then
        assertEquals(true, validSignIn);
    }

    @Test
    public void when_administrator_wants_to_sign_in() {
        //Given
        Client client = new Client("Administrator1", "PasswordAdmin1");
        _login = new Login();
        _login.register(client, Role.ADMINISTRATOR);

        //When
        _login.signIn("Administrator1", "PasswordAdmin1", Role.ADMINISTRATOR);
        boolean validSignIn = _login.checkClientSignedIn(client, Role.ADMINISTRATOR);

        //Then
        assertEquals(true, validSignIn);
    }

    @Test(expected=RuntimeException.class)
    public void when_user_wants_to_sign_in_with_invalid_credentials() {
        //Given
        Client client = new Client("User1", "Password1");
        _login = new Login();
        _login.register(client, Role.USER);

        //When
        _login.signIn("WrongUser1", "WrongPassword1", Role.USER);
        _login.checkClientSignedIn(client, Role.USER);

        //Then
        fail(Role.USER.exceptionMessage);
    }

    @Test(expected=RuntimeException.class)
    public void when_administrator_wants_to_sign_in_with_invalid_credentials() {
        //Given
        Client client = new Client("Admin1", "AdminPassword1");
        _login = new Login();
        _login.register(client, Role.ADMINISTRATOR);

        //When
        _login.signIn("WrongAdmin1", "WrongAdminPassword1", Role.ADMINISTRATOR);
        _login.checkClientSignedIn(client, Role.ADMINISTRATOR);

        //Then
        fail(Role.ADMINISTRATOR.exceptionMessage);
    }

    @Test
    public void search_count_for_user_in_current_session(){
        //Given
        Client client = new Client("UserActiveSearchCount1", "UserActiveSearchCountPassword1");
        _login = new Login();
        _login.register(client, Role.USER);
        _login.signIn("UserActiveSearchCount1", "UserActiveSearchCountPassword1", Role.USER);

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
        _login = new Login();
        _login.register(client, Role.USER);
        _login.signIn("UserActiveSearchCountSignOut1", "UserActiveSearchCountSignOutPassword1", Role.USER);

        //And client carries out searches before signing out
        for (int i = 0; i < 4; i++) {
            client.performSearch();
        }

        //When
        _login.signOut(client, Role.USER);

        //Then
        assertEquals(0,client.getCurrentSessionSearchCount());
    }

    @Test
    public void total_search_count_for_user(){

        //Given
        Client client = new Client("UserTotalSearchCount1", "UserTotalSearchCountPassword1");
        _login = new Login();
        _login.register(client, Role.USER);
        _login.signIn("UserTotalSearchCount1", "UserTotalSearchCountPassword1", Role.USER);

        //And client carries out some searches while signed in
        for (int i = 0; i < 6; i++) {
            client.performSearch();
        }
        // Client signs out
        _login.signOut(client, Role.USER);

        //When
        // Client signs back in
        _login.signIn("UserTotalSearchCount1", "UserTotalSearchCountPassword1", Role.USER);

        // And carries out more searches
        for (int i = 0; i < 4; i++) {
            client.performSearch();
        }

        //Then
        assertEquals(10,client.getTotalSearchCount());
    }
}
