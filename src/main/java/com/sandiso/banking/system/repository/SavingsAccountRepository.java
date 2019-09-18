package com.sandiso.banking.system.repository;

import com.sandiso.banking.system.model.SavingsAccount;
import org.springframework.data.repository.CrudRepository;

public interface SavingsAccountRepository extends CrudRepository<SavingsAccount, Long> {
    SavingsAccount findByAccountNumber(int accountNumber);
}
