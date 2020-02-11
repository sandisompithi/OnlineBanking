package com.sandiso.banking.system.service;

import com.sandiso.banking.system.dao.FixedAccountRepository;
import com.sandiso.banking.system.dao.FixedTransactionRepository;
import com.sandiso.banking.system.dao.SavingsAccountRepository;
import com.sandiso.banking.system.dao.SavingsTransactionRepository;
import com.sandiso.banking.system.model.FixedTransaction;
import com.sandiso.banking.system.model.SavingsTransaction;
import com.sandiso.banking.system.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<FixedTransaction> findFixedTransactionList(String username) {
        User user = userService.findByEmail(username);
        List<FixedTransaction> fixedTransactionList = user.getFixedAccount().getFixedTransactionList();
        return fixedTransactionList;
    }

    public List<SavingsTransaction> findSavingsTransactionList(String username) {
        User user = userService.findByEmail(username);
        List<SavingsTransaction> savingsTransactionList = user.getSavingsAccount().getSavingsTransactionList();
        return savingsTransactionList;
    }

    public void saveFixedDepositTransaction(FixedTransaction fixedTransaction) {
        fixedTransactionRepository.save(fixedTransaction);
    }

    public void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction) {
        savingsTransactionRepository.save(savingsTransaction);
    }

    public void saveFixedWithdrawalTransaction(FixedTransaction fixedTransaction) {
        fixedTransactionRepository.save(fixedTransaction);
    }

    public void saveSavingsWithdrawalTransaction(SavingsTransaction savingsTransaction) {
        savingsTransactionRepository.save(savingsTransaction);
    }
}
