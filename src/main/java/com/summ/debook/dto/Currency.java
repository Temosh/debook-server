package com.summ.debook.dto;

/**
 * @author Serhii Tymoshenko
 */
public class Currency {
    private String id;
    private String code;
    private String sign;

    public Currency() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
