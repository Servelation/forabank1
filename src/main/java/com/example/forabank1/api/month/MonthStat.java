package com.example.forabank1.api.month;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MonthStat {
    @JsonProperty
    private double loss;

    @JsonProperty
    private double arrival;

    @JsonProperty
    private double difference;

    public MonthStat() {
    }

    public MonthStat(double arrival, double loss, double difference) {
        this.arrival = arrival;
        this.loss = loss;
        this.difference = difference;
    }

    public double getLoss() {
        return loss;
    }

    public void setLoss(double loss) {
        this.loss = loss;
    }

    public double getArrival() {
        return arrival;
    }

    public void setArrival(double arrival) {
        this.arrival = arrival;
    }

    public double getDifference() {
        return difference;
    }

    public void setDifference(double difference) {
        this.difference = difference;
    }
}
