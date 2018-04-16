package store.exceptions;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
public class ProductVendorNotFoundException extends DAOException {

    /**
     * Exception with message for situation when order wasn't found
     *
     * @param message message for exception
     */
    public ProductVendorNotFoundException(String message) {
        super(message);
    }

    /**
     * exception with message and throwable for situation when order wasn't found
     *
     * @param message   message for exception
     * @param throwable object for exception
     */
    public ProductVendorNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
