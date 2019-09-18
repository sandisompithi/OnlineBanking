package com.sandiso.banking.system.repository;

import com.sandiso.banking.system.model.SavingsTransaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SavingsTransactionsRepository extends CrudRepository<SavingsTransaction, Long> {
    List<SavingsTransaction> findAll();
}
