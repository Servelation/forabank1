package com.example.forabank1.controller;

import com.example.forabank1.api.main.DirectionType;
import com.example.forabank1.api.main.RequestData;
import com.example.forabank1.api.main.SumFilterType;
import com.example.forabank1.api.main.SumSortType;
import com.example.forabank1.api.main.Tenor;
import com.example.forabank1.api.main.TenorFilterType;
import com.example.forabank1.api.main.TenorSortingType;
import com.example.forabank1.api.main.TypeOfOperation;
import com.example.forabank1.domain.FastPaymentData;
import com.example.forabank1.domain.Operation;
import com.example.forabank1.domain.Type;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OperationProcessor {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final long COUNT_OF_SEC_IN_DAY = 86_400L;

    public List<Operation> process(List<Operation> operations, RequestData request) {
        List<Operation> processingOperations = mainProcess(operations, request);
        return processingOperations;
    }

    public Map<Type, List<Operation>> group(List<Operation> operations, RequestData request)
        throws JsonProcessingException
    {
        List<Operation> processingOperations = mainProcess(operations, request);
        Map<Type, List<Operation>> map = processingOperations.stream()
            .collect(Collectors.groupingBy(Operation::getType));
        return map;
    }

    private List<Operation> mainProcess(List<Operation> operations, RequestData request) {
        List<Operation> processingOperations = new ArrayList<>(operations);
        processingOperations = processingOperations.stream()
            .map(operation -> operation.setDate(operation.getDate() / 1000))
            .map(operation -> operation.setTranDate(operation.getTranDate() / 1000))
            .collect(Collectors.toList());
        String period = request.getPeriod();
        if (period != null) {
            processingOperations = processPeriod(processingOperations, period);
        }

        Double sum = request.getSum();
        if (sum != null) {
            processingOperations = processSum(processingOperations, sum,
                request.getSumFilterType(), request.getSumSortType());
        }

        Tenor tenor = request.getTenor();
        if (tenor != null) {
            processingOperations = processTenor(processingOperations, tenor,
                request.getTenorFilterEffect(), request.getTenorSortingEffect());
        }

        String transferee = request.getTransferee();
        if (transferee != null) {
            processingOperations = processTransferee(processingOperations, transferee);
        }

        TypeOfOperation typeOfOperation = request.getTypeOfOperation();
        if (typeOfOperation != null) {
            processingOperations = processComment(processingOperations, typeOfOperation);
        }
        return processingOperations;
    }

    public List<Operation> processPeriod(List<Operation> operations, String period) {
        String[] dates = period.split("-");
        long beginDate = LocalDate.parse(dates[0], DATE_FORMATTER).toEpochDay() * COUNT_OF_SEC_IN_DAY;
        long endDate = LocalDate.parse(dates[1], DATE_FORMATTER).toEpochDay() * COUNT_OF_SEC_IN_DAY;
        return operations.stream()
            .filter(operation -> operation.getDate() >= beginDate && operation.getDate() <= endDate)
            .collect(Collectors.toList());
    }

    public List<Operation> processTenor(List<Operation> operations, Tenor tenor, TenorFilterType tenorFilterType,
        TenorSortingType tenorSortingType)
    {
        long nowDays = LocalDate.now().toEpochDay();
        long dateOfCount = nowDays - tenor.getCountOfDays();

        if (tenorSortingType == null) {
            tenorSortingType = TenorSortingType.SORT_DOWN;
        }
        List<Operation> processingOperations = new ArrayList<>(operations);
        if (tenorFilterType != null) {
            if (tenorFilterType == TenorFilterType.EXACT) {
                processingOperations = processingOperations.stream()
                    .filter(operation -> operation.getDate() == dateOfCount)
                    .collect(Collectors.toList());
            } else if (tenorFilterType == TenorFilterType.FILTER_BEFORE) {
                processingOperations = processingOperations.stream()
                    .filter(operation -> operation.getDate() > dateOfCount)
                    .collect(Collectors.toList());
            } else if (tenorFilterType == TenorFilterType.FILTER_AFTER) {
                processingOperations = processingOperations.stream()
                    .filter(operation -> operation.getDate() < dateOfCount)
                    .collect(Collectors.toList());
            }
        }
        if (tenorSortingType == TenorSortingType.SORT_UP) {
            return processingOperations.stream()
                .sorted()
                .collect(Collectors.toList());
        } else if (tenorSortingType == TenorSortingType.SORT_DOWN) {
            return processingOperations.stream()
                .sorted((num1, num2) -> (int) (num2.getDate() - num1.getDate()))
                .collect(Collectors.toList());
        }
        return processingOperations;
    }


    public List<Operation> processSum(List<Operation> operations, double sum,
        SumFilterType sumFilterType, SumSortType sumSortType)
    {
        if (sumSortType == null) {
            sumSortType = SumSortType.SORT_DOWN;
        }
        List<Operation> processingOperations = new ArrayList<>(operations);
        if (sumFilterType != null) {
            if (sumFilterType == SumFilterType.FILTER_AFTER) {
                processingOperations = processingOperations.stream()
                    .filter(operation -> operation.getAmount() < sum)
                    .collect(Collectors.toList());
            } else if (sumFilterType == SumFilterType.FILTER_BEFORE) {
                processingOperations = processingOperations.stream()
                    .filter(operation -> operation.getAmount() > sum)
                    .collect(Collectors.toList());
            }
        }
        if (sumSortType == SumSortType.SORT_DOWN) {
            return processingOperations.stream()
                .sorted((num1, num2) -> (int) (num2.getAmount() - num1.getAmount()))
                .collect(Collectors.toList());
        } else if (sumSortType == SumSortType.SORT_UP) {
            return processingOperations.stream()
                .sorted()
                .collect(Collectors.toList());
        }
        return processingOperations;
    }

    public List<Operation> processDirection(List<Operation> operations, DirectionType directionType) {
        if (directionType == null) {
            return operations;
        }
        List<Operation> processingOperations = new ArrayList<>(operations);
        if (directionType == DirectionType.INSIDE) {
            return processingOperations.stream()
                .filter(operation -> operation.getType() == Type.INSIDE)
                .collect(Collectors.toList());
        } else if (directionType == DirectionType.OUTSIDE) {
            return processingOperations.stream()
                .filter(operation -> operation.getType() == Type.OUTSIDE)
                .collect(Collectors.toList());
        }
        return processingOperations;
    }

    public List<Operation> processTransferee(List<Operation> operations, String transfereeName) {
        return operations.stream()
            .filter(operation -> {
                if (operation.getType() == Type.INSIDE) {
                    FastPaymentData fastPaymentData = operation.getFastPaymentData();
                    return fastPaymentData.getForeignName().equals(transfereeName);
                }
                return operation.getMerchantName().equals(transfereeName);
            })
            .collect(Collectors.toList());
    }

    public List<Operation> processComment(List<Operation> operations, TypeOfOperation type) {
        return operations.stream()
            .filter(operation -> isOperationMatches(operation, type))
            .collect(Collectors.toList());
    }

    private boolean isOperationMatches(Operation operation, TypeOfOperation type) {
        for (String name : type.getNames()) {
            if (operation.getComment().startsWith(name)) {
                return true;
            }
        }
        return false;
    }
}
