package com.summ.debook.service;

import com.summ.debook.dto.DebtRequestData;
import com.summ.debook.dto.Request;

import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
public interface RequestService {
    void createRequest(Request request);
    void updateRequest(String requestId, Request request);
    Request getRequest(String requestId);
    List<Request> getRequests();
    List<Request> getPendingRequests();
    List<DebtRequestData> getRequestDebtDataHistory(String requestId);
    void deleteRequest(String requestId);
}
