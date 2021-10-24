package com.example.forabank1.api.month;

import com.example.forabank1.api.main.TypeOfOperation;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WasteByType {

    @JsonProperty
    private TypeOfOperation type;

    @JsonProperty
    private double percent;

    @JsonProperty
    private double amount;

    public WasteByType() {

    }

    public WasteByType(TypeOfOperation type, double percent, double amount) {
        this.type = type;
        this.percent = percent;
        this.amount = amount;
    }

    public TypeOfOperation getType() {
        return type;
    }

    public void setType(TypeOfOperation type) {
        this.type = type;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
