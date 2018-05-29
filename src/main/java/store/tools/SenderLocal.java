package store.tools;

import javax.ejb.Local;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
@Local
public interface SenderLocal {
    public void sendMessage();
}
