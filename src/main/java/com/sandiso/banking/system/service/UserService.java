package com.sandiso.banking.system.service;

import com.sandiso.banking.system.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);
    User save(User user);

    List<User> findUserList();

    void enableUser(String username);

    void disableUser(String username);
}
