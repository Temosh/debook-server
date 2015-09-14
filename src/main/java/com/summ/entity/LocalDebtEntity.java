package com.summ.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Serhii Tymoshenko
 */
@Entity
@Table(name = "local_debt", schema = "", catalog = "debook_db")
@IdClass(LocalDebtEntityPK.class)
public class LocalDebtEntity {
    private int userId;
    private int currencyId;
    private int localUserId;
    private BigDecimal value;

    @Id
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "currency_id")
    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    @Id
    @Column(name = "local_user_id")
    public int getLocalUserId() {
        return localUserId;
    }

    public void setLocalUserId(int localUserId) {
        this.localUserId = localUserId;
    }

    @Basic
    @Column(name = "value")
    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocalDebtEntity that = (LocalDebtEntity) o;

        if (userId != that.userId) return false;
        if (currencyId != that.currencyId) return false;
        if (localUserId != that.localUserId) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + currencyId;
        result = 31 * result + localUserId;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
