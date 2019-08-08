package com.summ.debook.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Serhii Tymoshenko
 */
@Entity
@Table(name = "currency")
public class CurrencyEntity implements java.io.Serializable {

    @Id
    @Column(name = "currency_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "code", nullable = false, updatable = false, length = 45)
    private String code;

    @Column(name = "sign", nullable = false, updatable = false, length = 45)
    private String sign;

    public CurrencyEntity() {
    }

    public CurrencyEntity(Long id, String code, String sign) {
        this.id = id;
        this.code = code;
        this.sign = sign;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
