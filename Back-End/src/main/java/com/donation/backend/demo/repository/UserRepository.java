package com.donation.backend.demo.repository;


import com.donation.backend.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query(value = "SELECT * FROM users LEFT JOIN user_roles ON users.id=user_roles.user_id LEFT JOIN roles ON user_roles.role_id = roles.id WHERE roles.name = ?1", nativeQuery = true)
    List<User> findUsersByRoles(String role);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
