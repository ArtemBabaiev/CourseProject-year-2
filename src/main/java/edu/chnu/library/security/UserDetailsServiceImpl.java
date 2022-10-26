package edu.chnu.library.security;

import edu.chnu.library.model.Key;
import edu.chnu.library.repository.sql.KeySqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 26.09.2022 01:33
 * @class UserDetailsServiceImpl
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private KeySqlRepository keyRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Key key = keyRepository.findKeyByLogin(username);

        if (key == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        return new MyUserDetails(key);
    }

    public MyUserDetails getDetailsByLogin(String login) {
        Key key = keyRepository.findKeyByLogin(login);
        if (key == null) {
            return null;
        }
        return new MyUserDetails(key);
    }
}
