package com.example.forabank1.controller;

import com.example.forabank1.api.month.MonthStat;
import com.example.forabank1.domain.Operation;
import com.example.forabank1.domain.Type;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MonthProcessor {


    public MonthStat process(List<Operation> operations, LocalDate date) {
        List<Operation> processingOperations = new ArrayList<>(operations);
        processingOperations = processingOperations.stream()
            .map(operation -> operation.setDate(operation.getDate() / 1000))
            .map(operation -> operation.setTranDate(operation.getTranDate() / 1000))
            .collect(Collectors.toList());
        long endDays = findEpochDayOfTheNextMonth(date);
        long beginDays = date.toEpochDay();
        processingOperations = processingOperations.stream()
            .filter(operation -> operation.getDate() >= beginDays && operation.getDate() < endDays)
            .collect(Collectors.toList());
        double prihod = processingOperations.stream()
            .filter(operation -> operation.getType() == Type.INSIDE)
            .mapToDouble(Operation::getAmount)
            .sum();
        double yhod = processingOperations.stream()
            .filter(operation -> operation.getType() == Type.OUTSIDE)
            .mapToDouble(Operation::getAmount)
            .sum();
        double raznica = prihod - yhod;
        return new MonthStat(prihod, yhod, raznica);
    }

    // всегда 1 число даем
    private long findEpochDayOfTheNextMonth(LocalDate date) {
        Month month = date.getMonth();
        for (int i = 27; i < 33; i++) {
            Month tempMonth = date.plusDays(i).getMonth();
            if (tempMonth != month) {
                return date.plusDays(i).toEpochDay();
            }
        }
        System.out.println("НЕ НУ ЭТО УЖЕ НИ В КАКИЕ ВОРОТА"); //TODO: clean
        return date.plusDays(30).toEpochDay();
    }
}
