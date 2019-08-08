package com.summ.debook.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.summ.debook.type.RequestType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Serhii Tymoshenko
 */
@Entity
@Table(name = "request")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE")
public class RequestEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    protected Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, updatable = false)
    protected RequestType type;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "source_user_id", nullable = false, updatable = false)
    protected UserEntity sourceUser;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "target_user_id", nullable = false, updatable = false)
    protected UserEntity targetUser;

    @Column(name = "message")
    protected String message;

    @Column(name = "reject_message")
    protected String rejectMessage;

    @Column(name = "rejected")
    protected Boolean rejected;

    @Column(name = "processed", nullable = false)
    protected Boolean processed;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "last_updater", nullable = false)
    protected UserEntity lastUpdater;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "create_time", nullable = false)
    protected Timestamp createTime;

    @JsonIgnore
    @UpdateTimestamp
    @Column(name = "update_time", nullable = false)
    protected Timestamp updateTime;

    public Long getId() {
        return id;
    }

    public RequestType getType() {
        return type;
    }

    public void setType(RequestType requestType) {
        this.type = requestType;
    }

    public UserEntity getSourceUser() {
        return sourceUser;
    }

    public void setSourceUser(UserEntity sourceUser) {
        this.sourceUser = sourceUser;
    }

    public UserEntity getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(UserEntity targetUser) {
        this.targetUser = targetUser;
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

    public UserEntity getLastUpdater() {
        return lastUpdater;
    }

    public void setLastUpdater(UserEntity lastUpdater) {
        this.lastUpdater = lastUpdater;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }
}
