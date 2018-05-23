package nz.ac.auckland;

public enum Role {
    USER("User authentication failed - invalid username or password provided for user"),
    ADMINISTRATOR("Administrator authentication failed - invalid username or password provided for administrator");

    public String exceptionMessage = "";

    Role(String exceptionMessage){
        this.exceptionMessage = exceptionMessage;
    }
}