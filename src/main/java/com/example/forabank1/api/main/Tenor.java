package com.example.forabank1.api.main;

public enum Tenor {
    YEAR(365),
    MONTH(30),
    WEEK(7),
    DAY(1);

    private final int countOfDays;
    Tenor(int countOfDays) {
        this.countOfDays = countOfDays;
    }

    public int getCountOfDays() {
        return countOfDays;
    }
}
