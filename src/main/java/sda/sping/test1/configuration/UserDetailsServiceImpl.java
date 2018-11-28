package sda.sping.test1.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sda.sping.test1.dto.User;
import sda.sping.test1.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userFromDB = userRepository.findByUsername(username);
        if (userFromDB == null) {
            return null;
        }

        String role = userFromDB.getRole();

        return org.springframework.security.core.userdetails.User.builder()
                .username(username)
                .password(userFromDB.getPassword())
                .roles(role)
                .build();
    }
}
