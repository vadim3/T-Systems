package store.exceptions;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
public class DuplicateProductVendorException extends RuntimeException {
    public DuplicateProductVendorException(String message) {
        super(message);
    }

    public DuplicateProductVendorException(String message, Throwable cause) {
        super(message, cause);
    }
}
