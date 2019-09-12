package com.sandiso.banking.system.service;

import com.sandiso.banking.system.model.User;
import com.sandiso.banking.system.validation.UserRegistration;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);
    User save(UserRegistration registration);
}
