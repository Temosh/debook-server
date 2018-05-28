package com.summ.debook.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.summ.debook.type.EventSubtype;
import com.summ.debook.type.EventType;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Serhii Tymoshenko
 */
@Entity
@Table(name = "event_type")
public class EventTypeEntity {

    @JsonIgnore
    @Id
    @Column(name = "event_type_id")
    private Integer eventTypeId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private EventType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "subtype")
    private EventSubtype subtype;

    public Integer getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(Integer eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public EventSubtype getSubtype() {
        return subtype;
    }

    public void setSubtype(EventSubtype direction) {
        this.subtype = direction;
    }
}
