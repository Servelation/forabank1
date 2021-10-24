package com.example.forabank1.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LastInside {
    @JsonProperty
    private String name;

    @JsonProperty
    private Double count;

    @JsonProperty
    private Double percent;

    public LastInside() {
    }

    public LastInside(String name, Double count, Double percent) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }
}
