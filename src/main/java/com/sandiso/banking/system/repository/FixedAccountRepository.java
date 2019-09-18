package com.sandiso.banking.system.repository;

import com.sandiso.banking.system.model.FixedAccount;
import org.springframework.data.repository.CrudRepository;

public interface FixedAccountRepository extends CrudRepository<FixedAccount, Long> {
    FixedAccount findByAccountNumber(int accountNumber);
}
