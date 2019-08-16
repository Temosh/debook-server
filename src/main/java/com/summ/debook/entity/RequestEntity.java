package com.summ.debook.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.summ.debook.type.RequestType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

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
    @Column(name = "type", nullable = false, updatable = false)
    private RequestType type;

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

    @OneToMany(mappedBy = "request")
    @Where(clause = "processed = 0")
    protected List<DebtRequestDataEntity> debtRequestDataList;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "create_time", updatable = false)
    protected Timestamp createTime;

    @JsonIgnore
    @UpdateTimestamp
    @Column(name = "update_time")
    protected Timestamp updateTime;

    @Override
    public String toString() {
        return "RequestEntity{" +
                "id=" + id +
                ", type=" + type +
                ", sourceUser=" + sourceUser.getUserId() +
                ", targetUser=" + targetUser.getUserId() +
                ", message='" + message + '\'' +
                ", rejectMessage='" + rejectMessage + '\'' +
                ", rejected=" + rejected +
                ", processed=" + processed +
                ", lastUpdater=" + lastUpdater.getUserId() +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", debtRequestDataList=" + debtRequestDataList +
                '}';
    }

    public Long getId() {
        return id;
    }

    public RequestType getType() {
        return type;
    }

    public void setType(RequestType type) {
        this.type = type;
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

    public Boolean isRejected() {
        return rejected;
    }

    public void setRejected(Boolean rejected) {
        this.rejected = rejected;
    }

    public Boolean isProcessed() {
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

    public List<DebtRequestDataEntity> getDebtRequestDataList() {
        return debtRequestDataList;
    }

    public void setDebtRequestDataList(List<DebtRequestDataEntity> debtRequestDataList) {
        this.debtRequestDataList = debtRequestDataList;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }
}
