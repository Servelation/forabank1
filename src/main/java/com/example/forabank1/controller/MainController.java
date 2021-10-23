package com.example.forabank1.controller;

import com.example.forabank1.api.OperationResponse;
import com.example.forabank1.api.main.GroupNode;
import com.example.forabank1.api.main.RequestData;
import com.example.forabank1.api.month.MonthRequest;
import com.example.forabank1.api.month.MonthStat;
import com.example.forabank1.domain.Operation;
import com.example.forabank1.domain.OperationOut;
import com.example.forabank1.domain.Type;
import com.example.forabank1.parsing.Parser;
import com.example.forabank1.repo.FastPaymentDataRepo;
import com.example.forabank1.repo.OperationRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class MainController {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
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
    public OperationResponse main(HttpEntity<String> httpEntity) throws JsonProcessingException {
        List<Operation> operations = operationRepo.findAll();
        String body = httpEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        RequestData data = mapper.readValue(body, RequestData.class);
        OperationResponse processedOperations = new OperationProcessor().process(operations, data);
        return processedOperations;
    }

    @RequestMapping(value = "/group", method = POST)
    public List<GroupNode> grouping(HttpEntity<String> httpEntity) throws JsonProcessingException {
        List<Operation> operations = operationRepo.findAll();
        String body = httpEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        RequestData data = mapper.readValue(body, RequestData.class);
        Map<Type, List<OperationOut>> map = new OperationProcessor().group(operations, data);
        List<GroupNode> nodes = map.entrySet().stream()
            .map(entry -> new GroupNode(entry.getKey(), entry.getValue()))
            .collect(Collectors.toList());
        return nodes;
    }

    @RequestMapping(value = "/month", method = POST)
    public MonthStat monthStat(HttpEntity<String> httpEntity) throws JsonProcessingException {
        List<Operation> operations = operationRepo.findAll();
        String body = httpEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        MonthRequest data = mapper.readValue(body, MonthRequest.class);
        String strDate = "01." + data.getDate();
        LocalDate date = LocalDate.parse(strDate, DATE_FORMATTER);
        return new MonthProcessor().process(operations, date);
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
