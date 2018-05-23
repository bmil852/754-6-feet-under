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

    public void signIn(String username, String password, Role roleType) {
        for (Client c : _registered.get(roleType)) {
            if (c.getUsername().equals(username) && c.getPassword().equals(password)) {
                _active.get(roleType).add(c);
            }
        }
    }

    public boolean checkClientSignedIn(Client client, Role roleType){
        boolean isSignedIn = false;
        try {
            for (Client c : _active.get(roleType)) {
                if (c.getUsername().equals(client.getUsername()) && c.getPassword().equals(client.getPassword())) {
                    isSignedIn = true;
                    return isSignedIn;
                }
            }
        throw new RuntimeException();
        } catch (RuntimeException e){
            if(roleType.equals(Role.USER)) {
                throw new RuntimeException(Role.USER.exceptionMessage);
            }else if(roleType.equals(Role.ADMINISTRATOR)) {
                throw new RuntimeException(Role.ADMINISTRATOR.exceptionMessage);
            }
        }
        return isSignedIn;
    }

    public List<String> getRegistered(Role roleType) {
        List<String> list = new ArrayList<String>();
        for (Client c : _registered.get(roleType)) {
            list.add(c.getUsername());
        }
        return list;
    }
}
