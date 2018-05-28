package com.summ.debook.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Serhii Tymoshenko
 */
@Entity
@Table(name = "event")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "event_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "event_type_id", nullable = false)
    private EventTypeEntity eventType;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "affected_user_id", nullable = false)
    private UserEntity affectedUser;

    @Column(name = "processed")
    private Boolean processed;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "create_time")
    private Timestamp createTime;

    @JsonIgnore
    @UpdateTimestamp
    @Column(name = "update_time")
    private Timestamp updateTime;

    public Integer getId() {
        return id;
    }

    public EventTypeEntity getEventType() {
        return eventType;
    }

    public void setEventType(EventTypeEntity eventType) {
        this.eventType = eventType;
    }

    public UserEntity getAffectedUser() {
        return affectedUser;
    }

    public void setAffectedUser(UserEntity affectedUser) {
        this.affectedUser = affectedUser;
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
