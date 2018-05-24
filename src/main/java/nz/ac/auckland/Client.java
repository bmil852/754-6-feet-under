package nz.ac.auckland;

public class Client {
    private String _username;
    private String _password;
    private int _userCurrentSessionSearchCount;

    public Client(String username, String password) {
        _username = username;
        _password = password;
        _userCurrentSessionSearchCount =0;
    }

    public String getUsername() {
        return _username;
    }

    public String getPassword() {
        return _password;
    }

    public void performSearch(){
            _userCurrentSessionSearchCount++;
    }

    public int getCurrentSessionSearchCount(){
        return _userCurrentSessionSearchCount;
    }

    public void clearCurrentSessionSearchCount(){
        _userCurrentSessionSearchCount =0;
    }
}
