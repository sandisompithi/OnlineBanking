package com.sandiso.banking.system.service;

import com.sandiso.banking.system.model.*;

import java.security.Principal;
import java.util.List;

public interface TransactionService {
    List<FixedTransaction> findFixedTransactionList(String username);

    List<SavingsTransaction> findSavingsTransactionList(String username);

    void saveFixedDepositTransaction(FixedTransaction fixedTransactions);

    void saveSavingsDepositTransaction(SavingsTransaction savingsTransactions);

    void saveFixedWithdrawTransaction(FixedTransaction fixedTransactions);

    void saveSavingsWithdrawTransaction(SavingsTransaction savingsTransactions);

    void betweenAccountsTransfer(String transferFrom, String transferTo, String amount,
                                 FixedAccount fixedAccount,
                                 SavingsAccount savingsAccount) throws Exception;

    List<Recipient> findRecipientList(Principal principal);

    Recipient saveRecipient(Recipient recipient);

    Recipient findRecipientByName(String recipientName);

    void deleteRecipientByName(String recipientName);

    void toSomeoneElseTransfer(Recipient recipient, String accountType,
                               String amount, FixedAccount fixedAccount,
                               SavingsAccount savingsAccount);


}
