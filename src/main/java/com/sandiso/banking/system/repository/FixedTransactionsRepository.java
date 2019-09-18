package com.sandiso.banking.system.repository;

import com.sandiso.banking.system.model.FixedTransaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FixedTransactionsRepository extends CrudRepository<FixedTransaction, Long> {
    List<FixedTransaction> findAll();
}
