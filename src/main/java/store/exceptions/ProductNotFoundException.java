package store.exceptions;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
public class ProductNotFoundException extends DAOException {

    /**
     * Exception with message for situation when order wasn't found
     *
     * @param message message for exception
     */
    public ProductNotFoundException(String message) {
        super(message);
    }

    /**
     * exception with message and throwable for situation when order wasn't found
     *
     * @param message   message for exception
     * @param throwable object for exception
     */
    public ProductNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
