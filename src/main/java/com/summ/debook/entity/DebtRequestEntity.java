package com.summ.debook.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Serhii Tymoshenko
 */
@Entity
@Table(name = "debt_request")
public class DebtRequestEntity extends RequestEntity {

    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false, updatable = false)
    private CurrencyEntity currency;

    @ManyToOne
    @JoinColumn(name = "credit_type_id", nullable = false)
    private CreditTypeEntity creditType;

    @Column(name = "value")
    private BigDecimal value;

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
