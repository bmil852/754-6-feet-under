package nz.ac.auckland.unit;

import nz.ac.auckland.Client;
import nz.ac.auckland.Login;
import nz.ac.auckland.Role;
import org.junit.Test;

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
        boolean validSignIn = _login.checkUserSignedIn(client, Role.USER);

        //Then
        assertEquals(true, validSignIn);
    }
}
