package com.uneb.labweb.spring_project.enums;

public enum Status {
    ACTIVE("Ativo"), INACTIVE("Inativo");
    private String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
