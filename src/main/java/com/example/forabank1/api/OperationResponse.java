package com.example.forabank1.api;

import com.example.forabank1.domain.OperationOut;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OperationResponse {
    @JsonProperty
    public Integer currentPage;

    @JsonProperty
    public Integer countOfPages;

    @JsonProperty
    public Boolean ableToPagination;

    @JsonProperty
    public List<OperationOut> operations;


    public OperationResponse() {
    }

    public OperationResponse(Integer currentPage, Integer countOfPages, Boolean isAbleToPagination, List<OperationOut> operations) {
        this.currentPage = currentPage;
        this.countOfPages = countOfPages;
        this.ableToPagination = isAbleToPagination;
        this.operations = operations;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getCountOfPages() {
        return countOfPages;
    }

    public void setCountOfPages(Integer countOfPages) {
        this.countOfPages = countOfPages;
    }

    public Boolean getAbleToPagination() {
        return ableToPagination;
    }

    public void setAbleToPagination(Boolean ableToPagination) {
        ableToPagination = ableToPagination;
    }

    public List<OperationOut> getOperations() {
        return operations;
    }

    public void setOperations(List<OperationOut> operations) {
        this.operations = operations;
    }

}
