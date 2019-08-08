package com.summ.debook.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Serhii Tymoshenko
 */
public enum CreditType {
    LOAN("LOAN"),
    DEBT("DEBT");

    private final String value;
    private final static Map<String, CreditType> CONSTANTS = new HashMap<>();

    static {
        for (CreditType c : values()) {
            CONSTANTS.put(c.value, c);
        }
    }

    CreditType(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
        return this.value;
    }

    @JsonCreator
    public static CreditType fromValue(String value) {
        CreditType constant = CONSTANTS.get(value);
        if (constant == null) {
            throw new IllegalArgumentException(value);
        } else {
            return constant;
        }
    }
}