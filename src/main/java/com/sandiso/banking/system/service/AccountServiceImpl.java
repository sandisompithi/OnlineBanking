package com.sandiso.banking.system.service;

import com.sandiso.banking.system.dao.FixedAccountRepository;
import com.sandiso.banking.system.dao.SavingsAccountRepository;
import com.sandiso.banking.system.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;

@Service
public class AccountServiceImpl implements AccountService {
    private static int nextAccountNumber = 1234567890;

    @Autowired
    private FixedAccountRepository fixedAccountRepository;
    @Autowired
    private SavingsAccountRepository savingsAccountRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionService transactionService;

    public FixedAccount createFixedAccount() {
        FixedAccount fixedAccount = new FixedAccount();
        fixedAccount.setAccountBalance(new BigDecimal(0.0));
        fixedAccount.setAccountNumber(generateAccountNumber());

        fixedAccountRepository.save(fixedAccount);
        return fixedAccountRepository.findByAccountNumber(fixedAccount.getAccountNumber());
    }

    public SavingsAccount createSavingsAccount() {
        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setAccountBalance(new BigDecimal(0.0));
        savingsAccount.setAccountNumber(generateAccountNumber());

        savingsAccountRepository.save(savingsAccount);
        return savingsAccountRepository.findByAccountNumber(savingsAccount.getAccountNumber());
    }

    private int generateAccountNumber() {
        return ++nextAccountNumber;
    }

    public void deposit(Transactions transactions, Principal principal) {
        User user = userService.findByEmail(principal.getName());

        if (transactions.getAccountType().equalsIgnoreCase("Fixed")) {
            FixedAccount fixedAccount = user.getFixedAccount();
            fixedAccount.setAccountType(transactions.getAccountType());
            fixedAccount.setAccountBalance(fixedAccount.getAccountBalance().add(new BigDecimal(transactions.getAmount())));
            fixedAccountRepository.save(fixedAccount);

            Date date = new Date();

            FixedTransaction fixedTransaction = new FixedTransaction(date,"Deposit to fixed account","Deposit","Transaction successful",
                    transactions.getAmount(), fixedAccount.getAccountBalance(), fixedAccount);
            transactionService.saveFixedDepositTransaction(fixedTransaction);
        } else if (transactions.getAccountType().equalsIgnoreCase("Savings")) {
            SavingsAccount savingsAccount = user.getSavingsAccount();
            savingsAccount.setAccountType(transactions.getAccountType());
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(transactions.getAmount())));
            savingsAccountRepository.save(savingsAccount);

            Date date = new Date();

            SavingsTransaction savingsTransaction = new SavingsTransaction(date,"Deposit to savings account","Deposit","Transaction successful",
                    transactions.getAmount(), savingsAccount.getAccountBalance(), savingsAccount);
            transactionService.saveSavingsDepositTransaction(savingsTransaction);
        }
    }

    public void withdraw(Transactions transactions, Principal principal) {
        User user = userService.findByEmail(principal.getName());

        if (transactions.getAccountType().equalsIgnoreCase("Savings")) {
            FixedAccount fixedAccount = user.getFixedAccount();
            fixedAccount.setAccountType(transactions.getAccountType());
            fixedAccount.setAccountBalance(fixedAccount.getAccountBalance().subtract(new BigDecimal(transactions.getAmount())));
            fixedAccountRepository.save(fixedAccount);

            Date date = new Date();

            FixedTransaction fixedTransaction = new FixedTransaction(date,"Withdrew from fixed account","Withdrawal","Transaction successful",
                    transactions.getAmount(), fixedAccount.getAccountBalance(), fixedAccount);
            transactionService.saveFixedDepositTransaction(fixedTransaction);
        } else if (transactions.getAccountType().equalsIgnoreCase("Savings")) {
            SavingsAccount savingsAccount = user.getSavingsAccount();
            savingsAccount.setAccountType(transactions.getAccountType());
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(transactions.getAmount())));
            savingsAccountRepository.save(savingsAccount);

            Date date = new Date();

            SavingsTransaction savingsTransaction = new SavingsTransaction(date,"Withdrew from savings account","Withdrawal","Transaction successful",
                    transactions.getAmount(), savingsAccount.getAccountBalance(), savingsAccount);
            transactionService.saveSavingsDepositTransaction(savingsTransaction);
        }
    }
}
