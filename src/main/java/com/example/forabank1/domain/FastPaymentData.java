package com.example.forabank1.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fast_payment")
public class FastPaymentData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonProperty
    @Column(name = "foreignName")
    private String foreignName;

    @JsonProperty
    @Column(name = "foreignPhoneNumber")
    private String foreignPhoneNumber;

    @JsonProperty
    @Column(name = "foreignBankBIC")
    private String foreignBankBIC;

    @JsonProperty
    @Column(name = "foreignBankID")
    private String foreignBankID;

    @JsonProperty
    @Column(name = "foreignBankName")
    private String foreignBankName;

    @JsonProperty
    @Column(name = "documentComment")
    private String documentComment;

    public FastPaymentData() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getForeignName() {
        return foreignName;
    }

    public void setForeignName(String foreignName) {
        this.foreignName = foreignName;
    }

    public String getForeignPhoneNumber() {
        return foreignPhoneNumber;
    }

    public void setForeignPhoneNumber(String foreignPhoneNumber) {
        this.foreignPhoneNumber = foreignPhoneNumber;
    }

    public String getForeignBankBIC() {
        return foreignBankBIC;
    }

    public void setForeignBankBIC(String foreignBankBIC) {
        this.foreignBankBIC = foreignBankBIC;
    }

    public String getForeignBankID() {
        return foreignBankID;
    }

    public void setForeignBankID(String foreignBankID) {
        this.foreignBankID = foreignBankID;
    }

    public String getForeignBankName() {
        return foreignBankName;
    }

    public void setForeignBankName(String foreignBankName) {
        this.foreignBankName = foreignBankName;
    }

    public String getDocumentComment() {
        return documentComment;
    }

    public void setDocumentComment(String documentComment) {
        this.documentComment = documentComment;
    }
}
