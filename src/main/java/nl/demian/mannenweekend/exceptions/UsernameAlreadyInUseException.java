package nl.demian.mannenweekend.exceptions;

public class UsernameAlreadyInUseException extends RuntimeException {

    public UsernameAlreadyInUseException(String message) {
        super(message);
    }
}
