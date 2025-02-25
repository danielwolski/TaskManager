package com.calendarapp.repository;

import com.calendarapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);

    Boolean existsByUsername(String username);

    Boolean existsByLogin(String login);
}
