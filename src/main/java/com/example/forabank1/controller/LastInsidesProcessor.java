package com.example.forabank1.controller;

import com.example.forabank1.api.LastInside;
import com.example.forabank1.domain.Operation;
import com.example.forabank1.domain.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LastInsidesProcessor {
    private static final int COUNT = 5;
    List<LastInside> process(List<Operation> operations) {
        List<Operation> processingOperations = new ArrayList<>(operations);
        processingOperations = processingOperations.stream()
            .filter(operation -> operation.getType() == Type.INSIDE)
            .filter(operation -> operation.getFastPaymentData() != null)
            .map(operation -> operation.setDate(operation.getDate() / 1000))
            .map(operation -> operation.setTranDate(operation.getTranDate() / 1000))
            .sorted((op1, op2) -> (int) (op1.getDate() - op2.getDate()))
            .collect(Collectors.toList());
        int count = 0;
        List<String> tempNames = new ArrayList<>();
        List<LastInside> lastInsides = new ArrayList<>();
        for (Operation operation : processingOperations) {
            String name = operation.getFastPaymentData().getForeignName();
            if (tempNames.contains(operation.getFastPaymentData().getForeignName())) {
               LastInside lastInside = lastInsides.stream()
                    .filter(inside -> inside.getName().equals(name))
                    .findFirst().get();
               lastInside.setCount(lastInside.getCount() + operation.getAmount());
            } else {
                tempNames.add(name);
                lastInsides.add(new LastInside(name, operation.getAmount(), 0.0));
                count++;
                if (count == COUNT) {
                    break;
                }
            }
        }
        double totalSum = lastInsides.stream()
            .mapToDouble(LastInside::getCount)
            .sum();
        for (LastInside lastInside : lastInsides) {
            lastInside.setPercent((lastInside.getCount() * 100) / totalSum);
        }
        return lastInsides;
    }

}
