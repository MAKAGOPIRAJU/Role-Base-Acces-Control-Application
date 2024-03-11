package BankApplication.RBAC.Excetpions;

public class UserAlreadyRegisterd extends Exception{
    public UserAlreadyRegisterd(String message) {
        super(message);
    }
}
