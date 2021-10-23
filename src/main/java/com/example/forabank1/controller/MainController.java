package com.example.forabank1.controller;

import com.example.forabank1.api.RequestData;
import com.example.forabank1.domain.FastPaymentData;
import com.example.forabank1.domain.Operation;
import com.example.forabank1.parsing.Parser;
import com.example.forabank1.repo.FastPaymentDataRepo;
import com.example.forabank1.repo.OperationRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class MainController {

    private final FastPaymentDataRepo fastPaymentDataRepo;
    private final OperationRepo operationRepo;
    @Autowired
    Environment environment;

    public MainController(FastPaymentDataRepo fastPaymentDataRepo, OperationRepo operationRepo) {
        this.fastPaymentDataRepo = fastPaymentDataRepo;
        this.operationRepo = operationRepo;
        initializeData();
        System.out.println("data has been initialized"); //TODO: clean
    }

    @RequestMapping(value = "/", method = POST)
    public byte[] main(HttpEntity<String> httpEntity) throws JsonProcessingException {
        String json = httpEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        RequestData data = mapper.readValue(json, RequestData.class);
        List<Operation> operations = operationRepo.findAll();

        String resultJson = new OperationProcessor().process(operations, data);
        return resultJson.getBytes(StandardCharsets.UTF_8);
    }

    public void initializeData() {
        operationRepo.deleteAll();
        List<Operation> operations = new Parser().findAndParse();
        operationRepo.saveAll(operations);
    }


    @RequestMapping(value = "/test")
    public String main() {
        String msg = "Your server is up and running at port:" + environment.getProperty("local.server.port") + "\n" +
            "Address: " + environment.getProperty("local.server.host");

        return msg;
    }
}
