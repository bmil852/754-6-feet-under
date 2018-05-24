package nz.ac.auckland;

public class Client {
    private String _username;
    private String _password;
    private int _currentSessionSearchCount;
    private int _totalSearchCount;

    public Client(String username, String password) {
        _username = username;
        _password = password;
        _currentSessionSearchCount =0;
        _totalSearchCount =0;
    }

    public String getUsername() {
        return _username;
    }

    public String getPassword() {
        return _password;
    }

    public void performSearch(){
        _currentSessionSearchCount++;
        _totalSearchCount++;
    }

    public int getCurrentSessionSearchCount(){
        return _currentSessionSearchCount;
    }

    public int getTotalSearchCount(){
        return _totalSearchCount;
    }

    public void clearCurrentSessionSearchCount(){
        _currentSessionSearchCount =0;
    }
}
