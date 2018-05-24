package nz.ac.auckland;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountManagementService {

    private Map<Role,List<Client>> _registered;
    private Map<Role,List<Client>> _active;

    public AccountManagementService() {
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

    public void signOut(Client client, Role roleType) {
        for (Client c : _active.get(roleType)) {
            if (c.getUsername().equals(client.getUsername()) && c.getPassword().equals(client.getPassword())) {
                c.clearCurrentSessionSearchCount();
                _active.get(roleType).remove(c);
                break;
            }
        }
    }

    public boolean checkClientSignedIn(Client client, Role roleType){
        try {
            for (Client c : _active.get(roleType)) {
                if (c.getUsername().equals(client.getUsername()) && c.getPassword().equals(client.getPassword())) {
                    return true;
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
        return false;
    }

    public boolean checkClientRegistered(String username, String password, Role roleType) {
        for (Client client : _registered.get(roleType)) {
            if(client.getUsername() == username && client.getPassword() == password){
                return true;
            }
        }
        return false;
    }

    public boolean checkClientActive(String username, String password, Role roleType) {
        for (Client client : _active.get(roleType)) {
            if(client.getUsername() == username && client.getPassword() == password){
                return true;
            }
        }
        return false;
    }

    public int getNumberOfRegisteredUsers(Role roleType) {
        int numberOfRegisteredUsers = 0;
        if(roleType.equals(Role.ADMINISTRATOR)) {
            for (int i=0; i<_registered.get(Role.USER).size(); i++) {
                numberOfRegisteredUsers++;
            }
        }
        return numberOfRegisteredUsers;
    }
}
