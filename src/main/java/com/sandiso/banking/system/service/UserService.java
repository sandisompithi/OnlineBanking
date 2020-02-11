package com.sandiso.banking.system.service;

import com.sandiso.banking.system.model.User;

import java.util.Optional;

public interface UserService {
    User save(User user);
    User update(User user);

    User findByEmail(String username);

    Optional<User> find(Long id);
}
