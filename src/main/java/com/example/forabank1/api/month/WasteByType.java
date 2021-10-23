package com.example.forabank1.api.month;

import com.example.forabank1.api.main.TypeOfOperation;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WasteByType {

    @JsonProperty
    private TypeOfOperation type;

    @JsonProperty
    private double percent;

    public WasteByType() {

    }

    public WasteByType(TypeOfOperation type, double percent) {
        this.type = type;
        this.percent = percent;
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
}
