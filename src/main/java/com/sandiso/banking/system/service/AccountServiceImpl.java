package com.sandiso.banking.system.service;

import com.sandiso.banking.system.model.*;
import com.sandiso.banking.system.repository.FixedAccountRepository;
import com.sandiso.banking.system.repository.SavingsAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
    public void deposit(String accountType, double amount, Principal principal) {
        User user = userService.findByEmail(principal.getName());

        if (accountType.equalsIgnoreCase("Fixed")){
            FixedAccount fixedAccount = user.getFixedAccount();
            fixedAccount.setAccountBalance(fixedAccount.getAccountBalance().add(new BigDecimal(amount)));
            fixedAccountRepository.save(fixedAccount);

            Date date = new Date();

            FixedTransaction fixedTransaction = new FixedTransaction(date, "Deposit to Fixed Account", "Account", "Successful", amount, fixedAccount.getAccountBalance(), fixedAccount);
            transactionService.saveFixedDepositTransaction(fixedTransaction);
        } else if (accountType.equalsIgnoreCase("Savings")){
            SavingsAccount savingsAccount = user.getSavingsAccount();
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));
            savingsAccountRepository.save(savingsAccount);

            Date date = new Date();
            SavingsTransaction savingsTransaction = new SavingsTransaction(date,"Deposit to Savings Account", "Account", "Successful", amount, savingsAccount.getAccountBalance(), savingsAccount);
            transactionService.saveSavingsDepositTransaction(savingsTransaction);
        }
    }

    @Override
    public void withdraw(String accountType, double amount, Principal principal) {
        User user = userService.findByEmail(principal.getName());

        if (accountType.equalsIgnoreCase("Primary")){
            FixedAccount fixedAccount = user.getFixedAccount();
            fixedAccount.setAccountBalance(fixedAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            fixedAccountRepository.save(fixedAccount);

            Date date = new Date();

            FixedTransaction fixedTransaction = new FixedTransaction(date, "Withdrawal from fixed account","Account","Successful", amount, fixedAccount.getAccountBalance(), fixedAccount);
            transactionService.saveFixedWithdrawalTransaction(fixedTransaction);

        } else if (accountType.equalsIgnoreCase("Savings")){
            SavingsAccount savingsAccount = user.getSavingsAccount();
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            savingsAccountRepository.save(savingsAccount);

            Date date = new Date();

            SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Withdrawal from savings account","Account","Successful", amount, savingsAccount.getAccountBalance(), savingsAccount);
            transactionService.saveSavingsWithdrawalTransaction(savingsTransaction);
        }
    }

    private int generateAccountNumber(){
        return ++nextAccountNumber;
    }
}
