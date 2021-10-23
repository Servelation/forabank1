package com.example.forabank1.api.main;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public enum TypeOfOperation {
    COMMISSION(Arrays.asList("Комиссия")),
    PEREVOD(Arrays.asList("Перевод", "Перечисление")),
    SPISANIE(Arrays.asList("Списание")),
    ZACHISL(Arrays.asList("Зачисление")),
    UNKNOWN(Arrays.asList("Неизвестно"));
    private List<String> names;
    TypeOfOperation(List<String> name) {
        this.names = name;
    }

    @Override
    public String toString() {
        return this.names.get(0);
    }

    public List<String> getNames() {
        return names;
    }
}
