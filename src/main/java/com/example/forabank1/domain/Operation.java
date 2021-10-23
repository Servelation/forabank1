package com.example.forabank1.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "operations")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonProperty
    @Column(name = "type")
    private Type type;

    @JsonProperty
    @Column(name = "date")
    private long date;

    @JsonProperty
    @Column(name = "tranDate")
    private long tranDate;

    @JsonProperty
    @Column(name = "operationType")
    private OperationType operationType;

    @JsonProperty
    @Column(name = "amount")
    private double amount;

    @JsonProperty
    @Column(name = "comment")
    private String comment;

    @JsonProperty
    @Column(name = "accountNumber")
    private String accountNumber;

    @JsonProperty
    @Column(name = "currencyCodeNumeric")
    private int currencyCodeNumeric;

    @JsonProperty
    @Column(name = "merchantName")
    private String merchantName;

    @JsonProperty
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "fastPaymentData", nullable = true)
    private FastPaymentData fastPaymentData;

    @JsonProperty
    @Column(name = "MCC")
    private int MCC;

    public Operation() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public long getDate() {
        return date;
    }

    public Operation setDate(long date) {
        this.date = date;
        return this;
    }

    public long getTranDate() {
        return tranDate;
    }

    public Operation setTranDate(long tranDate) {
        this.tranDate = tranDate;
        return this;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getCurrencyCodeNumeric() {
        return currencyCodeNumeric;
    }

    public void setCurrencyCodeNumeric(int currencyCodeNumeric) {
        this.currencyCodeNumeric = currencyCodeNumeric;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public FastPaymentData getFastPaymentData() {
        return fastPaymentData;
    }

    public void setFastPaymentData(FastPaymentData fastPaymentData) {
        this.fastPaymentData = fastPaymentData;
    }

    public int getMCC() {
        return MCC;
    }

    public void setMCC(int MCC) {
        this.MCC = MCC;
    }
}
