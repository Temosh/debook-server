package com.summ.entity;

import javax.persistence.*;

/**
 * @author Serhii Tymoshenko
 */
@Entity
@Table(name = "credit_type", schema = "", catalog = "debook_db")
public class CreditTypeEntity {
    private int creditTypeId;
    private String type;

    @Id
    @Column(name = "credit_type_id")
    public int getCreditTypeId() {
        return creditTypeId;
    }

    public void setCreditTypeId(int creditTypeId) {
        this.creditTypeId = creditTypeId;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreditTypeEntity that = (CreditTypeEntity) o;

        if (creditTypeId != that.creditTypeId) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = creditTypeId;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
