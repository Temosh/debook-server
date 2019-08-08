package com.summ.debook.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Serhii Tymoshenko
 */
public enum RequestType {
    CONNECTION("CONNECTION"),
    DEBT("DEBT");

    private final String value;
    private final static Map<String, RequestType> CONSTANTS = new HashMap<>();

    static {
        for (RequestType c : values()) {
            CONSTANTS.put(c.value, c);
        }
    }

    RequestType(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
        return this.value;
    }

    @JsonCreator
    public static RequestType fromValue(String value) {
        RequestType constant = CONSTANTS.get(value);
        if (constant == null) {
            throw new IllegalArgumentException(value);
        } else {
            return constant;
        }
    }
}
