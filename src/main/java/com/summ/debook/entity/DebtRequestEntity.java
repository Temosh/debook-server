package com.summ.debook.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Serhii Tymoshenko
 */
@Entity
@Table(name = "debt_request")
public class DebtRequestEntity {

    @Id
    @Column(name = "request_id")
    protected Long id;

    @OneToOne(optional = false)
    @PrimaryKeyJoinColumn
    protected RequestEntity request;

    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false, updatable = false)
    protected CurrencyEntity currency;

    @ManyToOne
    @JoinColumn(name = "credit_type_id", nullable = false)
    protected CreditTypeEntity creditType;

    @Column(name = "value", nullable = false)
    protected BigDecimal value;

    public RequestEntity getRequest() {
        return request;
    }

    public void setRequest(RequestEntity request) {
        this.request = request;
    }

    public CurrencyEntity getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEntity currency) {
        this.currency = currency;
    }

    public CreditTypeEntity getCreditType() {
        return creditType;
    }

    public void setCreditType(CreditTypeEntity creditType) {
        this.creditType = creditType;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
