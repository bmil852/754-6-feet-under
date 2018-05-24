package nz.ac.auckland;

public class Client {
    private String _username;
    private String _password;
    private int _searchCount =0;

    public Client(String username, String password) {
        _username = username;
        _password = password;
    }

    public String getUsername() {
        return _username;
    }

    public String getPassword() {
        return _password;
    }

    public void performSearch(){
        _searchCount++;
    }

    public int getCurrentSessionSearchCount(){
        return _searchCount;
    }
}
