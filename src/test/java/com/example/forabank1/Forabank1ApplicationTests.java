package com.example.forabank1;

import com.example.forabank1.api.DirectionType;
import com.example.forabank1.api.RequestData;
import com.example.forabank1.api.SumSortType;
import com.example.forabank1.api.Tenor;
import com.example.forabank1.api.TenorSortingType;
import com.example.forabank1.domain.Type;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class Forabank1ApplicationTests {

    public static void main(String[] args) {

    }

    @Test
    void contextLoads() throws JsonProcessingException {
        RequestData requestData = new RequestData();
        requestData.setDirectionType(DirectionType.INSIDE);
        requestData.setPeriod("08.08.2021-15.10.2021");
        requestData.setTenor(Tenor.MONTH);
        requestData.setTenorSortingEffect(TenorSortingType.SORT_DOWN);
        requestData.setSumSortType(SumSortType.SORT_DOWN);
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(requestData);
        System.out.println(s); //TODO: clean
    }

}
