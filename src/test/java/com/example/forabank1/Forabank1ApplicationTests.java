package com.example.forabank1;

import com.example.forabank1.api.main.DirectionType;
import com.example.forabank1.api.main.RequestData;
import com.example.forabank1.api.main.SumSortType;
import com.example.forabank1.api.main.Tenor;
import com.example.forabank1.api.main.TenorSortingType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

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
