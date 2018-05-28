package com.summ.debook.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author Serhii Tymoshenko
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "request")
public class RequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    protected Integer id;

    @OneToOne
    @JoinColumn(name = "previous_request_id", nullable = false)
    protected RequestEntity previousRequest;

    @OneToOne
    @JoinColumn(name = "source_event_id", nullable = false)
    protected EventEntity sourceEvent;

    @OneToOne
    @JoinColumn(name = "target_event_id", nullable = false)
    protected EventEntity targetEvent;

    @Column(name = "message")
    protected String message;

    @Column(name = "reject_message")
    protected String rejectMessage;

    @Column(name = "rejected")
    protected Boolean rejected;

    @Column(name = "processed")
    protected Boolean processed;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "create_time")
    protected Timestamp createTime;

    @JsonIgnore
    @UpdateTimestamp
    @Column(name = "update_time")
    protected Timestamp updateTime;

    public Integer getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRejectMessage() {
        return rejectMessage;
    }

    public void setRejectMessage(String rejectMessage) {
        this.rejectMessage = rejectMessage;
    }

    public Boolean getRejected() {
        return rejected;
    }

    public void setRejected(Boolean rejected) {
        this.rejected = rejected;
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

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
