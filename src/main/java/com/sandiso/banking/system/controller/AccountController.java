package com.sandiso.banking.system.controller;

import com.sandiso.banking.system.dao.FixedAccountRepository;
import com.sandiso.banking.system.dao.FixedTransactionRepository;
import com.sandiso.banking.system.dao.SavingsAccountRepository;
import com.sandiso.banking.system.dao.SavingsTransactionRepository;
import com.sandiso.banking.system.model.*;
import com.sandiso.banking.system.service.AccountService;
import com.sandiso.banking.system.service.TransactionService;
import com.sandiso.banking.system.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private FixedAccountRepository fixedAccountRepository;
    @Autowired
    private SavingsAccountRepository savingsAccountRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private FixedTransactionRepository fixedTransactionRepository;
    @Autowired
    private SavingsTransactionRepository savingsTransactionRepository;

    // request method to create a new account by a guest
    @CrossOrigin
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User newUser) {

        if (userService.findByEmail(newUser.getUsername()) != null) {
            logger.error("username Already exist " + newUser.getUsername());
            return new ResponseEntity("user with username " + newUser.getUsername() + "already exist ",
                    HttpStatus.CONFLICT
            );
        }

        newUser.setRole("USER");

        return new ResponseEntity<User>(userService.save(newUser), HttpStatus.CREATED);

    }

    // this is the login api/service
    @CrossOrigin
    @RequestMapping("/login")
    public Principal user(Principal principal) {

        logger.info("user logged " + principal);
        return principal;

    }

    @CrossOrigin
    @GetMapping(value = "/fixedAccount/{accountNumber}")
    public FixedAccount getFixedAccount(@PathVariable(value = "accountNumber") int accountNumber) {
        return fixedAccountRepository.findByAccountNumber(accountNumber);
    }

    @CrossOrigin
    @GetMapping(value = "/savingsAccount/{accountNumber}")
    public SavingsAccount getSavingsAccount(@PathVariable(value = "accountNumber") int accountNumber) {
        return savingsAccountRepository.findByAccountNumber(accountNumber);
    }

    @CrossOrigin
    @PostMapping(value = "/deposit")
    public void deposit(@RequestBody Transactions transactions, Principal principal) {
        accountService.deposit(transactions, principal);
    }

    @CrossOrigin
    @PostMapping(value = "/withdraw")
    public void withdraw(@RequestBody Transactions transactions, Principal principal) {
        accountService.withdraw(transactions, principal);
    }

    @CrossOrigin
    @GetMapping(value = "/fixedTransaction")
    public List<FixedTransaction> fixedTransactionList(@RequestParam("username") String username) {

        return transactionService.findFixedTransactionList(username);

    }

    @CrossOrigin
    @GetMapping(value = "/savingsTransaction")
    public List<SavingsTransaction> savingsTransactionList() {
        return savingsTransactionRepository.findAll();
    }
}
