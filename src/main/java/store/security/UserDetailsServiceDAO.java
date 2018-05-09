package store.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import store.dao.interfaces.UserDAO;
import store.entities.User;
import store.exceptions.DAOException;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(user.getAccessLevel().getStatus()));
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
        } catch (DAOException ex) {
            throw new UsernameNotFoundException(email + " not found");
        } catch (Exception ex) {
            return null;
        }
    }
}
