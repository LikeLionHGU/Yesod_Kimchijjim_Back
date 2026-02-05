package org.example.yesodkimchijjimback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.yesodkimchijjimback.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
