package com.example.forabank1.parsing;

import com.example.forabank1.domain.Operation;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ParsingObject {
    @JsonProperty
    private String statusCode;
    @JsonProperty
    private String errorMessage;
    @JsonProperty
    private List<Operation> data;

    public ParsingObject() {

    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<Operation> getData() {
        return data;
    }

    public void setData(List<Operation> data) {
        this.data = data;
    }
}
