package com.sandiso.banking.system.service;

import com.sandiso.banking.system.model.FixedTransaction;
import com.sandiso.banking.system.model.SavingsTransaction;
import com.sandiso.banking.system.model.User;
import com.sandiso.banking.system.repository.FixedAccountRepository;
import com.sandiso.banking.system.repository.FixedTransactionsRepository;
import com.sandiso.banking.system.repository.SavingsAccountRepository;
import com.sandiso.banking.system.repository.SavingsTransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private FixedTransactionsRepository fixedTransactionsRepository;

    @Autowired
    private SavingsTransactionsRepository savingsTransactionsRepository;

    @Autowired
    private FixedAccountRepository fixedAccountRepository;

    @Autowired
    private SavingsAccountRepository savingsAccountRepository;

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
    public void saveFixedDepositTransaction(FixedTransaction fixedTransaction) {
        fixedTransactionsRepository.save(fixedTransaction);
    }

    @Override
    public void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction) {
        savingsTransactionsRepository.save(savingsTransaction);
    }

    @Override
    public void saveFixedWithdrawalTransaction(FixedTransaction fixedTransaction) {
        fixedTransactionsRepository.save(fixedTransaction);
    }

    @Override
    public void saveSavingsWithdrawalTransaction(SavingsTransaction savingsTransaction) {
        savingsTransactionsRepository.save(savingsTransaction);
    }
}
