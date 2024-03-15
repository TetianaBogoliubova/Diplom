package com.bogoliubova.training_service.repository;

import com.bogoliubova.training_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
   // User findByLogin(String login);

    Optional<User> findByLogin(String login);
    boolean existsByLogin(String login);

}
