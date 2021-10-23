package com.example.forabank1.api.main;

import com.example.forabank1.domain.Operation;
import com.example.forabank1.domain.OperationOut;
import com.example.forabank1.domain.Type;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GroupNode {

    @JsonProperty
    private Type type;

    @JsonProperty
    private List<OperationOut> operations;

    public GroupNode() {
    }

    public GroupNode(Type type, List<OperationOut> operations) {
        this.type = type;
        this.operations = operations;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<OperationOut> getOperations() {
        return operations;
    }

    public void setOperations(List<OperationOut> operations) {
        this.operations = operations;
    }
}
