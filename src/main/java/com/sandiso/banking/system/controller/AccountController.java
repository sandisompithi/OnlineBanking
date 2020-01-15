package com.sandiso.banking.system.controller;

import com.sandiso.banking.system.model.*;
import com.sandiso.banking.system.repository.FixedAccountRepository;
import com.sandiso.banking.system.repository.SavingsAccountRepository;
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

@CrossOrigin(origins = "http://localhost:4200")
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
    private FixedAccountRepository fixedAccountRepository;

    @Autowired
    private SavingsAccountRepository savingsAccountRepository;

    @Autowired
    private TransactionService transactionService;

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

    @RequestMapping("/login")
    public Principal user(Principal principal){
        logger.info("user logged " + principal);
        return principal;
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public void deposit(@RequestBody Transactions transactions, Principal principal) {
        accountService.deposit(transactions, principal);
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    public void withdraw(@RequestBody Transactions transactions, Principal principal) {
        accountService.withdraw(transactions, principal);
    }

    @RequestMapping(value = "/fixedAccount/{accountNumber}", method = RequestMethod.GET)
    public FixedAccount getCreatedFixedAccount(@PathVariable(value = "accountNumber") int accountNumber) {
        return fixedAccountRepository.findByAccountNumber(accountNumber);
    }

    @RequestMapping(value = "/savingsAccount/{accountNumber}", method = RequestMethod.GET)
    public SavingsAccount getCreatedSavingsAccount(@PathVariable(value = "accountNumber") int accountNumber) {
        return savingsAccountRepository.findByAccountNumber(accountNumber);
    }

    @RequestMapping(value = "/fixed/transaction", method = RequestMethod.GET)
    public List<FixedTransaction> getFixedTransactionList(@RequestParam("username") String username) {
        return transactionService.findFixedTransactionList(username);
    }

    @RequestMapping(value = "/savings/transaction", method = RequestMethod.GET)
    public List<SavingsTransaction> getSavingsTransactionList(@RequestParam("username") String username) {
        return transactionService.findSavingsTransactionList(username);
    }

    @RequestMapping(value = "/betweenAccounts", method = RequestMethod.POST)
    public ResponseEntity<?> betweenAccountsPost(@RequestParam("transferFrom") String tranferFrom,
                                                 @RequestParam("transferTo") String transferTo,
                                                 @RequestParam("amount") String amount, Principal principal) throws Exception {
        User user = userService.findByEmail(principal.getName());
        FixedAccount fixedAccount = user.getFixedAccount();
        SavingsAccount savingsAccount = user.getSavingsAccount();

        transactionService.betweenAccountsTransfer(tranferFrom, transferTo, amount, fixedAccount, savingsAccount);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/recipient", method = RequestMethod.GET)
    public List<Recipient> recipientList(Principal principal) {
        return transactionService.findRecipientList(principal);
    }

    @RequestMapping(value = "/recipient/save", method = RequestMethod.POST)
    public ResponseEntity<?> recipientPost(@RequestParam("recipient") Recipient recipient, Principal principal) {

        User user = userService.findByEmail(principal.getName());
        recipient.setUser(user);

        return ResponseEntity.ok(transactionService.saveRecipient(recipient));
    }

    @RequestMapping(value = "/toSomeoneElse", method = RequestMethod.GET)
    public List<Recipient> toSomeoneElse(Principal principal) {
        return transactionService.findRecipientList(principal);
    }

    @RequestMapping(value = "/toSomeoneElse", method = RequestMethod.POST)
    public ResponseEntity<?> toSomeoneElsePost(@RequestParam("recipientName") String recipientName,
                                               @RequestParam("accountType") String accountType,
                                               @RequestParam("amount") String amount, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        Recipient recipient = transactionService.findRecipientByName(recipientName);
        transactionService.toSomeoneElseTransfer(recipient, accountType, amount, user.getFixedAccount(), user.getSavingsAccount());

        return ResponseEntity.ok().build();
    }
}
