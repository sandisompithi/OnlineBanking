package com.sandiso.banking.system.repository;

import com.sandiso.banking.system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    List<User> findAll();
}
