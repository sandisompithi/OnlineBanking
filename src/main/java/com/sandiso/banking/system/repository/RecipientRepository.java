package com.sandiso.banking.system.repository;

import com.sandiso.banking.system.model.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipientRepository extends JpaRepository<Recipient, Long> {
    List<Recipient> findAll();
    Recipient findByName(String recipientName);
    void deleteByName(String recipientName);
}
