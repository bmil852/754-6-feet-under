package nz.ac.auckland;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Login {

    private Map<Role,List<Client>> _registered;
    private Map<Role,List<Client>> _active;

    public Login() {
        _registered = new HashMap<Role,List<Client>>();
        _active = new HashMap<Role,List<Client>>();

        _registered.put(Role.USER, new ArrayList<Client>());
        _registered.put(Role.ADMINISTRATOR, new ArrayList<Client>());
        _active.put(Role.USER, new ArrayList<Client>());
        _active.put(Role.ADMINISTRATOR, new ArrayList<Client>());
    }




    public void register(Client client, Role roleType) {
        if (client.getUsername() != null && client.getPassword() != null) {
            _registered.get(roleType).add(client);
        }
    }

    public boolean signIn(String username, String password, Role roleType) {
        boolean validSignIn = false;
        for (Client c : _registered.get(roleType)) {
            if (c.getUsername().equals(username) && c.getPassword().equals(password)) {
                _active.get(roleType).add(c);
                validSignIn = true;
            }
        }
        return validSignIn;

    }

}
