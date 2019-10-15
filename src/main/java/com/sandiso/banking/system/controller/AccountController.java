package com.sandiso.banking.system.controller;

import com.sandiso.banking.system.model.*;
import com.sandiso.banking.system.service.AccountService;
import com.sandiso.banking.system.service.TransactionService;
import com.sandiso.banking.system.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api")
public class AccountController {

    public static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @CrossOrigin
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user) {
        User exist = userService.findByEmail(user.getEmail());
        if (exist != null) {
            logger.error("username already exist " + user.getEmail());
            return new ResponseEntity("user with username " + user.getEmail() +
                    "already exist ", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<User>(userService.save(user), HttpStatus.CREATED);
    }

    @CrossOrigin
    @RequestMapping("/login")
    public Principal user(Principal principal){
        logger.info("user logged " + principal);
        return principal;
    }

    @CrossOrigin
    @RequestMapping(value = "/fixedAccount", method = RequestMethod.GET)
    public List<FixedTransaction> fixedAccount(String username) {
        return transactionService.findFixedTransactionList(username);
    }
}
