package com.example.forabank1.domain;

import com.example.forabank1.api.main.TypeOfOperation;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OperationOut {
    @JsonProperty
    private Type type;

    @JsonProperty
    private long date;

    @JsonProperty
    private long tranDate;

    @JsonProperty
    private OperationType operationType;

    @JsonProperty
    private double amount;

    @JsonProperty
    private String comment;

    @JsonProperty
    private String accountNumber;

    @JsonProperty
    private int currencyCodeNumeric;

    @JsonProperty
    private String merchantName;

    @JsonProperty
    private FastPaymentData fastPaymentData;

    @JsonProperty
    private int MCC;

    @JsonProperty
    private TypeOfOperation typeOfOperation;

    public OperationOut() {
    }

    public OperationOut(Operation operation, TypeOfOperation typeOfOperation) {
        this.type = operation.getType();
        this.date = operation.getDate();
        this.tranDate = operation.getTranDate();
        this.operationType = operation.getOperationType();
        this.amount = operation.getAmount();
        this.comment = operation.getComment();
        this.accountNumber = operation.getAccountNumber();
        this.currencyCodeNumeric = operation.getCurrencyCodeNumeric();
        this.fastPaymentData = operation.getFastPaymentData();
        this.MCC = operation.getMCC();
        this.typeOfOperation = typeOfOperation;
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

    public OperationOut setDate(long date) {
        this.date = date;
        return this;
    }

    public long getTranDate() {
        return tranDate;
    }

    public OperationOut setTranDate(long tranDate) {
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

    public TypeOfOperation getTypeOfOperation() {
        return typeOfOperation;
    }

    public void setTypeOfOperation(TypeOfOperation typeOfOperation) {
        this.typeOfOperation = typeOfOperation;
    }
}
