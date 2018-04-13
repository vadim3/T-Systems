package store.exceptions;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
public class DAOException extends RuntimeException{

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
