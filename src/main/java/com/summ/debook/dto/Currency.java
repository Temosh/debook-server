package com.summ.debook.dto;

/**
 * @author Serhii Tymoshenko
 */
public class Currency {
    private String id;
    private String code;
    private String sign;

    public Currency(String id, String code, String sign) {
        this.id = id;
        this.code = code;
        this.sign = sign;
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getSign() {
        return sign;
    }
}
