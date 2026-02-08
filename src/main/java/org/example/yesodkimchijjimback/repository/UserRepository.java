package org.example.yesodkimchijjimback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.yesodkimchijjimback.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByGoogleSub(String sub);

    Optional<User> findByEmail(String email);
}
