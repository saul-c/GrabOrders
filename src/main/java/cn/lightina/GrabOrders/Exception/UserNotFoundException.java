package cn.lightina.GrabOrders.Exception;

public class UserNotFoundException extends LoginException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
