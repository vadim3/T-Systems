package store.exceptions;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
public class DuplicateUserException extends RuntimeException {

    /**
     * Exception with message for situation for situation when user already exists
     *
     * @param message message for exception
     */
    public DuplicateUserException(String message) {
        super(message);
    }

    /**
     * exception with message and throwable for situation when user already exists
     *
     * @param message   message for exception
     * @param throwable object for exception
     */
    public DuplicateUserException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
