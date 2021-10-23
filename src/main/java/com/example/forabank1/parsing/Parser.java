package com.example.forabank1.parsing;

import com.example.forabank1.domain.Operation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class Parser {

    public List<Operation> findAndParse() {
        URI uri;
        try {
            uri = Parser.class.getClassLoader().getResource("Vypiska.json").toURI();
        } catch (URISyntaxException e) {
            throw new IllegalStateException("ПИЗДЕЦ ВСЕ УПАЛО НЕТ JSON ФАЙЛА!", e);
        }
        Path path = Paths.get(uri);
        String json = "";
        try {
            json = Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectMapper mapper = new ObjectMapper();
        ParsingObject parsingObject = null;
        try {
            parsingObject = mapper.readValue(json, ParsingObject.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return parsingObject == null ? Collections.emptyList() : parsingObject.getData();
    }
}
