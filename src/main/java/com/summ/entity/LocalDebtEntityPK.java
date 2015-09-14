package com.summ.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Serhii Tymoshenko
 */
public class LocalDebtEntityPK implements Serializable {
    private int userId;
    private int currencyId;
    private int localUserId;

    @Column(name = "user_id")
    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "currency_id")
    @Id
    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    @Column(name = "local_user_id")
    @Id
    public int getLocalUserId() {
        return localUserId;
    }

    public void setLocalUserId(int localUserId) {
        this.localUserId = localUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocalDebtEntityPK that = (LocalDebtEntityPK) o;

        if (userId != that.userId) return false;
        if (currencyId != that.currencyId) return false;
        if (localUserId != that.localUserId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + currencyId;
        result = 31 * result + localUserId;
        return result;
    }
}
