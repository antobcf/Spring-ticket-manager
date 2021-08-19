package com.usermanagement.UserManagement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    User findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.role = ?1")
    List<User> findByRole(String role);

    @Query("SELECT u FROM User u WHERE u.role != 'admin'")
    List<User> findNotAdmin();

}
