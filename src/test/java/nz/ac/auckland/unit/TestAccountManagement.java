package nz.ac.auckland.unit;

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
        boolean validSignIn = _login.signIn("User1", "Password1", Role.USER);

        //Then
        assertEquals(true, validSignIn);
    }
}
