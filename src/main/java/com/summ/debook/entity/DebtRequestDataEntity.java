package com.summ.debook.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author Serhii Tymoshenko
 */
@Entity
@Table(name = "debt_request_data")
public class DebtRequestDataEntity implements Serializable {

    @Id
    @Column(name = "debt_request_data_id")
    protected Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "request_id", nullable = false, updatable = false)
    protected RequestEntity request;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_user_id", nullable = false, updatable = false)
    protected UserEntity user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "currency_id", nullable = false, updatable = false)
    protected CurrencyEntity currency;

    @ManyToOne(optional = false)
    @JoinColumn(name = "credit_type_id", nullable = false, updatable = false)
    protected CreditTypeEntity creditType;

    @Column(name = "value", nullable = false, updatable = false)
    protected BigDecimal value;

    @Column(name = "message", updatable = false)
    protected String message;

    @Column(name = "processed", nullable = false)
    protected Boolean processed;

    @CreationTimestamp
    @Column(name = "create_time", updatable = false)
    protected Timestamp createTime;

    public RequestEntity getRequest() {
        return request;
    }

    public void setRequest(RequestEntity request) {
        this.request = request;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getProcessed() {
        return processed;
    }

    public void setProcessed(Boolean processed) {
        this.processed = processed;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }
}
