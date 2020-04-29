package co.ozdev.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OperationType {
    GET("GET"),
    GIVE("GIVE");

    private String name;

    OperationType(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
