package com.example.forabank1.api.month;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MonthRequest {
    @JsonProperty
    private String date;

    public MonthRequest() {
    }

    public MonthRequest(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
