package com.summ.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Serhii Tymoshenko
 */
public class DebtEntityPK implements Serializable {
    private int debtorUserId;
    private int creditorUserId;
    private int currencyId;

    @Column(name = "debtor_user_id")
    @Id
    public int getDebtorUserId() {
        return debtorUserId;
    }

    public void setDebtorUserId(int debtorUserId) {
        this.debtorUserId = debtorUserId;
    }

    @Column(name = "creditor_user_id")
    @Id
    public int getCreditorUserId() {
        return creditorUserId;
    }

    public void setCreditorUserId(int creditorUserId) {
        this.creditorUserId = creditorUserId;
    }

    @Column(name = "currency_id")
    @Id
    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DebtEntityPK that = (DebtEntityPK) o;

        if (debtorUserId != that.debtorUserId) return false;
        if (creditorUserId != that.creditorUserId) return false;
        if (currencyId != that.currencyId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = debtorUserId;
        result = 31 * result + creditorUserId;
        result = 31 * result + currencyId;
        return result;
    }
}
