package com.example.forabank1.api;

import com.example.forabank1.domain.Type;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestData {
    @JsonProperty
    private Integer page;

    @JsonProperty
    private Tenor tenor;

    @JsonProperty
    private TenorFilterType tenorFilterType;

    @JsonProperty
    private TenorSortingType tenorSortingType;

    @JsonProperty
    private String period;

    @JsonProperty
    private Double sum;

    @JsonProperty
    private SumFilterType sumFilterType;

    @JsonProperty
    private SumSortType sumSortType;

    @JsonProperty
    private DirectionType directionType;

    @JsonProperty
    private String transferee;

    public RequestData() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Tenor getTenor() {
        return tenor;
    }

    public void setTenor(Tenor tenor) {
        this.tenor = tenor;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public SumFilterType getSumFilterType() {
        return sumFilterType;
    }

    public void setSumFilterType(SumFilterType sumFilterType) {
        this.sumFilterType = sumFilterType;
    }

    public SumSortType getSumSortType() {
        return sumSortType;
    }

    public void setSumSortType(SumSortType sumSortType) {
        this.sumSortType = sumSortType;
    }

    public String getTransferee() {
        return transferee;
    }

    public void setTransferee(String transferee) {
        this.transferee = transferee;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public TenorFilterType getTenorFilterEffect() {
        return tenorFilterType;
    }

    public void setTenorFilterEffect(TenorFilterType tenorFilterType) {
        this.tenorFilterType = tenorFilterType;
    }

    public TenorSortingType getTenorSortingEffect() {
        return tenorSortingType;
    }

    public void setTenorSortingEffect(TenorSortingType tenorSortingType) {
        this.tenorSortingType = tenorSortingType;
    }

    public DirectionType getDirectionType() {
        return directionType;
    }

    public void setDirectionType(DirectionType directionType) {
        this.directionType = directionType;
    }
}
