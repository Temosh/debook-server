package com.summ.debook.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.summ.debook.type.CreditType;

import javax.persistence.*;

/**
 * @author Serhii Tymoshenko
 */
@Entity
@Table(name = "credit_type")
public class CreditTypeEntity implements java.io.Serializable {

    @JsonIgnore
    @Id
    @Column(name = "credit_type_id", unique = true, nullable = false)
    private Long creditTypeId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, updatable = false, length = 45)
    private CreditType type;

    public CreditTypeEntity() {
    }

    public CreditTypeEntity(Long creditTypeId, CreditType type) {
        this.creditTypeId = creditTypeId;
        this.type = type;
    }

    public Long getCreditTypeId() {
        return this.creditTypeId;
    }

    public void setCreditTypeId(Long creditTypeId) {
        this.creditTypeId = creditTypeId;
    }

    public CreditType getType() {
        return this.type;
    }

    public void setType(CreditType type) {
        this.type = type;
    }

}
