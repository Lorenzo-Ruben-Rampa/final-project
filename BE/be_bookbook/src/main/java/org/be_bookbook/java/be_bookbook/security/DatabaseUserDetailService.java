package org.be_bookbook.java.be_bookbook.security;
import java.util.Optional;
import org.be_bookbook.java.be_bookbook.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.be_bookbook.java.be_bookbook.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@Service
public class DatabaseUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
       Optional<User> userAttempt = userRepository.findByUsername(username);
       if (userAttempt.isEmpty()) {
           throw new UsernameNotFoundException("There are no users available with username: " + username);
       }
           return new DatabaseUserDetail(userAttempt.get());
    }
}