package com.sandiso.banking.system.service;

import com.sandiso.banking.system.model.FixedTransaction;
import com.sandiso.banking.system.model.SavingsTransaction;

import java.util.List;

public interface TransactionService {

    List<FixedTransaction> findFixedTransactionList(String username);
    List<SavingsTransaction> findSavingsTransactionList(String username);

    void saveFixedDepositTransaction(FixedTransaction fixedTransaction);
    void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction);

    void saveFixedWithdrawalTransaction(FixedTransaction fixedTransaction);
    void saveSavingsWithdrawalTransaction(SavingsTransaction savingsTransaction);

}
