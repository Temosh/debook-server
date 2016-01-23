package com.summ.debook.entity;
// Generated Jan 23, 2016 2:48:02 AM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * LocalDebtIdEntity generated by hbm2java
 */
@Embeddable
public class LocalDebtIdEntity implements java.io.Serializable {

    private int userId;
    private int currencyId;
    private int localUserId;

    public LocalDebtIdEntity() {
    }

    public LocalDebtIdEntity(int userId, int currencyId, int localUserId) {
        this.userId = userId;
        this.currencyId = currencyId;
        this.localUserId = localUserId;
    }

    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "currency_id", nullable = false)
    public int getCurrencyId() {
        return this.currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    @Column(name = "local_user_id", nullable = false)
    public int getLocalUserId() {
        return this.localUserId;
    }

    public void setLocalUserId(int localUserId) {
        this.localUserId = localUserId;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof LocalDebtIdEntity))
            return false;
        LocalDebtIdEntity castOther = (LocalDebtIdEntity) other;

        return (this.getUserId() == castOther.getUserId()) && (this.getCurrencyId() == castOther.getCurrencyId())
                && (this.getLocalUserId() == castOther.getLocalUserId());
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + this.getUserId();
        result = 37 * result + this.getCurrencyId();
        result = 37 * result + this.getLocalUserId();
        return result;
    }

}