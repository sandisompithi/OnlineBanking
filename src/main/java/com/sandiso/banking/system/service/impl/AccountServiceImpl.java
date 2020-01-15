package com.sandiso.banking.system.service.impl;

import com.sandiso.banking.system.model.*;
import com.sandiso.banking.system.repository.FixedAccountRepository;
import com.sandiso.banking.system.repository.SavingsAccountRepository;
import com.sandiso.banking.system.service.AccountService;
import com.sandiso.banking.system.service.TransactionService;
import com.sandiso.banking.system.service.UserService;
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

    @Override
    public FixedAccount createFixedAccount() {
        FixedAccount fixedAccount = new FixedAccount();
        fixedAccount.setAccountBalance(new BigDecimal(0.0));
        fixedAccount.setAccountNumber(generateAccountNumber());

        fixedAccountRepository.save(fixedAccount);
        return fixedAccountRepository.findByAccountNumber(fixedAccount.getAccountNumber());
    }

    @Override
    public SavingsAccount createSavingsAccount() {
        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setAccountBalance(new BigDecimal(0.0));
        savingsAccount.setAccountNumber(generateAccountNumber());

        savingsAccountRepository.save(savingsAccount);
        return savingsAccountRepository.findByAccountNumber(savingsAccount.getAccountNumber());
    }

    @Override
    public void deposit(Transactions transactions, Principal principal) {
        User user = userService.findByEmail(principal.getName());

        if (transactions.getAccountType().equalsIgnoreCase("Fixed")) {
            FixedAccount fixedAccount = user.getFixedAccount();
            fixedAccount.setAccountType(transactions.getAccountType());
            fixedAccount.setAccountBalance(fixedAccount.getAccountBalance().add(new BigDecimal(transactions.getAmount())));
            fixedAccountRepository.save(fixedAccount);

            Date date = new Date();

            FixedTransaction fixedTransaction = new FixedTransaction(date, "Deposit to fixed account","Deposit","Successful", transactions.getAmount(), fixedAccount.getAccountBalance(), fixedAccount);
            transactionService.saveFixedDepositTransaction(fixedTransaction);

        } else if (transactions.getAccountType().equalsIgnoreCase("Savings")) {
            SavingsAccount savingsAccount = user.getSavingsAccount();
            savingsAccount.setAccountType(transactions.getAccountType());
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(transactions.getAmount())));
            savingsAccountRepository.save(savingsAccount);

            Date date = new Date();

            SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Deposit to savings account","Deposit","Successful", transactions.getAmount(), savingsAccount.getAccountBalance(), savingsAccount);
            transactionService.saveSavingsDepositTransaction(savingsTransaction);
        }
    }

    @Override
    public void withdraw(Transactions transactions, Principal principal) {
        User user = userService.findByEmail(principal.getName());

        if (transactions.getAccountType().equalsIgnoreCase("Fixed")){
            FixedAccount fixedAccount = user.getFixedAccount();
            fixedAccount.setAccountType(transactions.getAccountType());
            fixedAccount.setAccountBalance(fixedAccount.getAccountBalance().subtract(new BigDecimal(transactions.getAmount())));
            fixedAccountRepository.save(fixedAccount);

            Date date = new Date();

            FixedTransaction fixedTransaction = new FixedTransaction(date, "Withdrawal from fixed account","Withdrawal","Successful", transactions.getAmount(), fixedAccount.getAccountBalance(), fixedAccount);
            transactionService.saveFixedWithdrawTransaction(fixedTransaction);

        } else if (transactions.getAccountType().equalsIgnoreCase("Savings")){
            SavingsAccount savingsAccount = user.getSavingsAccount();
            savingsAccount.setAccountType(transactions.getAccountType());
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(transactions.getAmount())));
            savingsAccountRepository.save(savingsAccount);

            Date date = new Date();

            SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Withdrawal from savings account","Withdrawal","Successful", transactions.getAmount(), savingsAccount.getAccountBalance(), savingsAccount);
            transactionService.saveSavingsWithdrawTransaction(savingsTransaction);
        }
    }

    private int generateAccountNumber(){
        return ++nextAccountNumber;
    }
}
