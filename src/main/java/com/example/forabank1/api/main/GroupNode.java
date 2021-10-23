package com.example.forabank1.api.main;

import com.example.forabank1.domain.Operation;
import com.example.forabank1.domain.Type;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GroupNode {

    @JsonProperty
    private Type type;

    @JsonProperty
    private List<Operation> operations;

    public GroupNode() {
    }

    public GroupNode(Type type, List<Operation> operations) {
        this.type = type;
        this.operations = operations;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }
}
