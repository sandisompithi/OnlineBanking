package com.sandiso.banking.system.repository;

import com.sandiso.banking.system.model.FixedAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FixedAccountRepository extends JpaRepository<FixedAccount, Long> {
    FixedAccount findByAccountNumber(int accountNumber);
}
