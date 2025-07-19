package org.be_bookbook.java.be_bookbook.repository;
import java.util.Optional;
import org.be_bookbook.java.be_bookbook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);    
}
