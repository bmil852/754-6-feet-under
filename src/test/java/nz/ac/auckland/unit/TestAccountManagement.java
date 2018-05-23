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
        fail("User authentication failed - invalid username or password provided for user");
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
        fail("Administrator authentication failed - invalid username or password provided for administrator");
    }
}
