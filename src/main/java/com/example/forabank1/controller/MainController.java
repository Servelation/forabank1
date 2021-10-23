package com.example.forabank1.controller;

import com.example.forabank1.api.RequestData;
import com.example.forabank1.domain.FastPaymentData;
import com.example.forabank1.domain.Operation;
import com.example.forabank1.parsing.Parser;
import com.example.forabank1.repo.FastPaymentDataRepo;
import com.example.forabank1.repo.OperationRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class MainController {

    private final FastPaymentDataRepo fastPaymentDataRepo;
    private final OperationRepo operationRepo;

    public MainController(FastPaymentDataRepo fastPaymentDataRepo, OperationRepo operationRepo) {
        this.fastPaymentDataRepo = fastPaymentDataRepo;
        this.operationRepo = operationRepo;
        initializeData();
        System.out.println("data has been initialized"); //TODO: clean
    }

    @RequestMapping(value = "/", method = POST)
    public String main(@RequestBody RequestData data) throws JsonProcessingException {
//        String json = httpEntity.getBody();
//        ObjectMapper mapper = new ObjectMapper();
//        RequestData data = mapper.readValue(json, RequestData.class);
        List<Operation> operations = operationRepo.findAll();

        String resultJson = new OperationProcessor().process(operations, data);
        return resultJson;
    }

    public void initializeData() {
        operationRepo.deleteAll();
        List<Operation> operations = new Parser().findAndParse();
        operationRepo.saveAll(operations);
    }
}
