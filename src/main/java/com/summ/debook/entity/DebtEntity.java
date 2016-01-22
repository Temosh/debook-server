package com.summ.debook.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Serhii Tymoshenko
 */
@Entity
@Table(name = "debt", schema = "", catalog = "debook_db")
@IdClass(DebtEntityPK.class)
public class DebtEntity {
    private int debtorUserId;
    private int creditorUserId;
    private int currencyId;
    private BigDecimal value;

    @Id
    @Column(name = "debtor_user_id")
    public int getDebtorUserId() {
        return debtorUserId;
    }

    public void setDebtorUserId(int debtorUserId) {
        this.debtorUserId = debtorUserId;
    }

    @Id
    @Column(name = "creditor_user_id")
    public int getCreditorUserId() {
        return creditorUserId;
    }

    public void setCreditorUserId(int creditorUserId) {
        this.creditorUserId = creditorUserId;
    }

    @Id
    @Column(name = "currency_id")
    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
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

        DebtEntity that = (DebtEntity) o;

        if (debtorUserId != that.debtorUserId) return false;
        if (creditorUserId != that.creditorUserId) return false;
        if (currencyId != that.currencyId) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = debtorUserId;
        result = 31 * result + creditorUserId;
        result = 31 * result + currencyId;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
