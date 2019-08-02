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
public class RequestEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    protected Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    protected RequestType type;

    @ManyToOne
    @JoinColumn(name = "parent_request_id")
    protected RequestEntity parentRequest;

    @OneToOne
    @JoinColumn(name = "previous_request_id")
    protected RequestEntity previousRequest;

    @ManyToOne
    @JoinColumn(name = "source_user_id", nullable = false)
    protected UserEntity sourceeUser;

    @ManyToOne
    @JoinColumn(name = "target_user_id", nullable = false)
    protected UserEntity targetUser;

    @Column(name = "message")
    protected String message;

    @Column(name = "reject_message")
    protected String rejectMessage;

    @Column(name = "rejected")
    protected Boolean rejected;

    @Column(name = "processed", nullable = false)
    protected Boolean processed;

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

    public RequestEntity getParentRequest() {
        return parentRequest;
    }

    public void setParentRequest(RequestEntity parentRequest) {
        this.parentRequest = parentRequest;
    }

    public RequestEntity getPreviousRequest() {
        return previousRequest;
    }

    public void setPreviousRequest(RequestEntity previousRequest) {
        this.previousRequest = previousRequest;
    }

    public UserEntity getSourceeUser() {
        return sourceeUser;
    }

    public void setSourceeUser(UserEntity sourceeUser) {
        this.sourceeUser = sourceeUser;
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
