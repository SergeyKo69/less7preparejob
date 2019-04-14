package ru.kogut.enterprise.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kogut.enterprise.model.dictionary.User;
import ru.kogut.enterprise.repository.UserRepository;

/**
 * @author S.Kogut on 2019-04-12
 */

@Service
public class SecurityUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(userName);
        if (user == null) {
            throw new UsernameNotFoundException(userName);
        }
        return new SecurityUserPrincipal(user);
    }

}
