package com.sandiso.banking.system.service;

import com.sandiso.banking.system.model.FixedAccount;
import com.sandiso.banking.system.model.SavingsAccount;

import java.security.Principal;

public interface AccountService {

    FixedAccount createFixedAccount();
    SavingsAccount createSavingsAccount();

    void deposit(String accountType, double amount, Principal principal);
    void withdraw(String accountType, double amount, Principal principal);
}