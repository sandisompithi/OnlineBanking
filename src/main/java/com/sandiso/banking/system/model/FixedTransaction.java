package com.sandiso.banking.system.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class FixedTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    private String reference;
    private String transactionType;
    private String status;
    private double amount;
    private BigDecimal availableBalance;

    @ManyToOne
    @JoinColumn(name = "fixed_account_id")
    private FixedAccount fixedAccount;

    public FixedTransaction() {
    }

    public FixedTransaction(Date date, String reference, String transactionType,
                            String status, double amount, BigDecimal availableBalance,
                            FixedAccount fixedAccount) {
        this.date = date;
        this.reference = reference;
        this.transactionType = transactionType;
        this.status = status;
        this.amount = amount;
        this.availableBalance = availableBalance;
        this.fixedAccount = fixedAccount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    public FixedAccount getFixedAccount() {
        return fixedAccount;
    }

    public void setFixedAccount(FixedAccount fixedAccount) {
        this.fixedAccount = fixedAccount;
    }
}
