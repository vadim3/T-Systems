package store.exceptions;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
public class UserNotFoundException extends DAOException {
    /**
     * Exception with message for situation when user wasn't found
     *
     * @param message message for exception
     */
    public UserNotFoundException(String message) {
        super(message);
    }

    /**
     * exception with message and throwable for situation when user wasn't found
     *
     * @param message   message for exception
     * @param throwable object for exception
     */
    public UserNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
