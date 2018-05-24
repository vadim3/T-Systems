package store.exceptions;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
public class DuplicateProductCategoryException extends RuntimeException{
    public DuplicateProductCategoryException(String message) {
        super(message);
    }

    public DuplicateProductCategoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
