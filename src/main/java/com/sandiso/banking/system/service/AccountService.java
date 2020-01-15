package com.sandiso.banking.system.service;

import com.sandiso.banking.system.model.FixedAccount;
import com.sandiso.banking.system.model.SavingsAccount;
import com.sandiso.banking.system.model.Transactions;

import java.security.Principal;

public interface AccountService {

    FixedAccount createFixedAccount();
    SavingsAccount createSavingsAccount();

    void deposit(Transactions transactions, Principal principal);
    void withdraw(Transactions transactions, Principal principal);
}
