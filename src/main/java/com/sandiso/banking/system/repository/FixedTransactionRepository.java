package com.sandiso.banking.system.repository;

import com.sandiso.banking.system.model.FixedTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FixedTransactionRepository extends JpaRepository<FixedTransaction, Long> {
    List<FixedTransaction> findAll();
}
