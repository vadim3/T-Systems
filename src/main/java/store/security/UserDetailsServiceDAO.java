package store.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import store.dao.interfaces.UserDAO;
import store.entities.User;
import store.exceptions.DAOException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
@Service("userDetailsService")
public class UserDetailsServiceDAO implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    /**
     * @param email - user Email
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        User user;
        try {
            user = this.userDAO.getUserByeMail(email);
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getAccessLevel().getStatus()));
            return new org.springframework.security.core.userdetails.User(email, user.getPassword(),
                    true, true, true, true, authorities);
        } catch (DAOException ex) {
            throw new UsernameNotFoundException(email + " not found");
        } catch (Exception ex) {
            return null;
        }
    }

}
