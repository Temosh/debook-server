package com.summ.entity;

import javax.persistence.*;

/**
 * @author Serhii Tymoshenko
 */
@Entity
@Table(name = "currency", schema = "", catalog = "debook_db")
public class CurrencyEntity {
    private int currencyId;
    private String code;
    private String sign;

    @Id
    @Column(name = "currency_id")
    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "sign")
    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurrencyEntity that = (CurrencyEntity) o;

        if (currencyId != that.currencyId) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (sign != null ? !sign.equals(that.sign) : that.sign != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = currencyId;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (sign != null ? sign.hashCode() : 0);
        return result;
    }
}
