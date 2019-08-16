package com.summ.debook.dto;

import com.summ.debook.type.RequestType;

import java.util.ArrayList;
import java.util.List;


/**
 * Structure that combines common fields for all requests
 */
public class Request {

    private String id;
    private RequestType type;
    private String userId;
    private String personId;
    private Boolean processed;
    private Boolean rejected;
    private String rejectMessage;
    private String message;
    private String lastUpdaterId;
    private List<DebtRequestData> data = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RequestType getType() {
        return type;
    }

    public void setType(RequestType type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public Boolean isProcessed() {
        return processed;
    }

    public void setProcessed(Boolean processed) {
        this.processed = processed;
    }

    public Boolean isRejected() {
        return rejected;
    }

    public void setRejected(Boolean rejected) {
        this.rejected = rejected;
    }

    public String getRejectMessage() {
        return rejectMessage;
    }

    public void setRejectMessage(String rejectMessage) {
        this.rejectMessage = rejectMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLastUpdaterId() {
        return lastUpdaterId;
    }

    public void setLastUpdaterId(String lastUpdaterId) {
        this.lastUpdaterId = lastUpdaterId;
    }

    public List<DebtRequestData> getData() {
        return data;
    }

    public void setData(List<DebtRequestData> data) {
        this.data = data;
    }
}
