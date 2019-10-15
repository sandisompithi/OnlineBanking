package com.sandiso.banking.system.repository;

import com.sandiso.banking.system.model.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {
    SavingsAccount findByAccountNumber(int accountNumber);
}
