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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api")
@PreAuthorize("hasRole('USER')")
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
    @RequestMapping(value = "/user/all", method = RequestMethod.GET)
    public List<User> userList() {
        return userService.findUserList();
    }

    @CrossOrigin
    @RequestMapping(value = "/fixedAccount", method = RequestMethod.GET)
    public ResponseEntity<FixedAccount> getFixedAccountById(@RequestBody Principal principal) {
        User user = userService.findByEmail(principal.getName());
        FixedAccount fixedAccount = user.getFixedAccount();

        return ResponseEntity.ok().body(fixedAccount);
    }

    @CrossOrigin
    @RequestMapping(value = "/savingsAccount", method = RequestMethod.GET)
    public ResponseEntity<SavingsAccount> getSavingsAccountById(@RequestBody Principal principal) {
        User user = userService.findByEmail(principal.getName());
        SavingsAccount savingsAccount = user.getSavingsAccount();

        return ResponseEntity.ok().body(savingsAccount);
    }

    @CrossOrigin
    @RequestMapping(value = "/fixedTransaction", method = RequestMethod.GET)
    public List<FixedTransaction> getFixedTransactionList(@RequestParam("username") String username) {
        return transactionService.findFixedTransactionList(username);
    }

    @CrossOrigin
    @RequestMapping(value = "/savingsTransaction", method = RequestMethod.GET)
    public List<SavingsTransaction> getSavingsTransactionList(@RequestParam("username") String username) {
        return transactionService.findSavingsTransactionList(username);
    }

    @CrossOrigin
    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public ResponseEntity<?> depositPost(@RequestParam("amount") String amount,
                                         @RequestParam("accountType") String accountType, Principal principal) {

        accountService.deposit(accountType, Double.parseDouble(amount), principal);

        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    public ResponseEntity<?> withdrawPost(@RequestParam("amount") String amount,
                                         @RequestParam("accountType") String accountType, Principal principal) {

        accountService.withdraw(accountType, Double.parseDouble(amount), principal);

        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @RequestMapping("/user/{username}/enable")
    public void enableUser(@PathVariable("username") String username) {
        userService.enableUser(username);
    }

    @CrossOrigin
    @RequestMapping("/user/{username}/disable")
    public void disableUser(@PathVariable("username") String username) {
        userService.disableUser(username);
    }
}
