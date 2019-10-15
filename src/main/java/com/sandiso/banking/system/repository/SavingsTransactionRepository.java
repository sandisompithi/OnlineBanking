package com.sandiso.banking.system.repository;

import com.sandiso.banking.system.model.SavingsTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavingsTransactionRepository extends JpaRepository<SavingsTransaction, Long> {
    List<SavingsTransaction> findAll();
}
