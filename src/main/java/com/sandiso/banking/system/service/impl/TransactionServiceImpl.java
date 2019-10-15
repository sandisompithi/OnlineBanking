package com.sandiso.banking.system.service.impl;

import com.sandiso.banking.system.model.*;
import com.sandiso.banking.system.repository.*;
import com.sandiso.banking.system.service.TransactionService;
import com.sandiso.banking.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private FixedTransactionRepository fixedTransactionRepository;

    @Autowired
    private SavingsTransactionRepository savingsTransactionRepository;

    @Autowired
    private FixedAccountRepository fixedAccountRepository;

    @Autowired
    private SavingsAccountRepository savingsAccountRepository;

    @Autowired
    private RecipientRepository recipientRepository;

    @Override
    public List<FixedTransaction> findFixedTransactionList(String username) {
        User user = userService.findByEmail(username);
        List<FixedTransaction> fixedTransactionList = user.getFixedAccount().getFixedTransactionList();
        return fixedTransactionList;
    }

    @Override
    public List<SavingsTransaction> findSavingsTransactionList(String username) {
        User user = userService.findByEmail(username);
        List<SavingsTransaction> savingsTransactionList = user.getSavingsAccount().getSavingsTransactionList();
        return savingsTransactionList;
    }

    @Override
    public void saveFixedDepositTransaction(FixedTransaction fixedTransactions) {
        fixedTransactionRepository.save(fixedTransactions);
    }

    @Override
    public void saveSavingsDepositTransaction(SavingsTransaction savingsTransactions) {
        savingsTransactionRepository.save(savingsTransactions);
    }

    @Override
    public void saveFixedWithdrawTransaction(FixedTransaction fixedTransactions) {
        fixedTransactionRepository.save(fixedTransactions);
    }

    @Override
    public void saveSavingsWithdrawTransaction(SavingsTransaction savingsTransactions) {
        savingsTransactionRepository.save(savingsTransactions);
    }

    @Override
    public void betweenAccountsTransfer(String transferFrom, String transferTo, String amount, FixedAccount fixedAccount, SavingsAccount savingsAccount) throws Exception {
        if (transferFrom.equalsIgnoreCase("Fixed") && transferTo.equalsIgnoreCase("Savings")){
            fixedAccount.setAccountBalance(fixedAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));
            fixedAccountRepository.save(fixedAccount);
            savingsAccountRepository.save(savingsAccount);

            Date date = new Date();
            FixedTransaction fixedTransactions = new FixedTransaction(date,
                    "Between account transfer from "+transferFrom+" to "+
                            transferTo, "Account", "Finished",
                    Double.parseDouble(amount), fixedAccount.getAccountBalance(), fixedAccount);
            fixedTransactionRepository.save(fixedTransactions);
        } else if (transferFrom.equalsIgnoreCase("Savings") && transferTo.equalsIgnoreCase("Fixed")){
            fixedAccount.setAccountBalance(fixedAccount.getAccountBalance().add(new BigDecimal(amount)));
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            fixedAccountRepository.save(fixedAccount);
            savingsAccountRepository.save(savingsAccount);

            Date date = new Date();
            SavingsTransaction savingsTransactions = new SavingsTransaction(date,
                    "Between account transfer from "+transferFrom+" to "+
                            transferTo, "Account", "Finished",
                    Double.parseDouble(amount), savingsAccount.getAccountBalance(), savingsAccount);
            savingsTransactionRepository.save(savingsTransactions);
        } else {
            throw new Exception("Couldn't complete transaction");
        }
    }

    @Override
    public List<Recipient> findRecipientList(Principal principal) {
        String username = principal.getName();

        List<Recipient> recipientList = recipientRepository.findAll().stream()
                .filter(recipient -> username.equals(recipient.getUser().getEmail()))
                .collect(Collectors.toList());
        return recipientList;
    }

    @Override
    public Recipient saveRecipient(Recipient recipient) {
        return recipientRepository.save(recipient);
    }

    @Override
    public Recipient findRecipientByName(String recipientName) {
        return recipientRepository.findByName(recipientName);
    }

    @Override
    public void deleteRecipientByName(String recipientName) {
        recipientRepository.deleteByName(recipientName);
    }

    @Override
    public void toSomeoneElseTransfer(Recipient recipient, String accountType, String amount, FixedAccount fixedAccount, SavingsAccount savingsAccount) {
        if (accountType.equalsIgnoreCase("Fixed")){
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            savingsAccountRepository.save(savingsAccount);

            Date date = new Date();

            SavingsTransaction savingsTransaction = new SavingsTransaction(date,
                    "Transfered to recipient " + recipient.getName(), "Pay", "Successful",
                    Double.parseDouble(amount), savingsAccount.getAccountBalance(), savingsAccount);
            savingsTransactionRepository.save(savingsTransaction);
        }
    }
}
