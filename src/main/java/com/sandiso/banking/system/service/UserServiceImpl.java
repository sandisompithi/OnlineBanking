package com.sandiso.banking.system.service;

import com.sandiso.banking.system.dao.UserRepository;
import com.sandiso.banking.system.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setFixedAccount(accountService.createFixedAccount());
        user.setSavingsAccount(accountService.createSavingsAccount());
        return userRepository.saveAndFlush(user);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public User findByEmail(String username) {
        return userRepository.findOneByUsername(username);
    }

    public Optional<User> find(Long id) {
        return userRepository.findById(id);
    }
}
