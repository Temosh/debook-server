package com.summ.debook.web;

import com.summ.debook.dto.DebtRequestData;
import com.summ.debook.dto.Request;
import com.summ.debook.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
@RestController
@RequestMapping("/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @PostMapping
    public void createRequest(@RequestBody Request request) {
        requestService.createRequest(request);
    }

    @PutMapping("/{requestId}")
    public void updateRequest(@RequestBody Request request, @PathVariable("requestId") String requestId) {
        requestService.updateRequest(requestId, request);
    }

    @GetMapping("/{requestId}")
    public Request getRequest(@PathVariable("requestId") String requestId) {
        return requestService.getRequest(requestId);
    }

    @GetMapping
    public List<Request> getRequests() {
        return requestService.getRequests();
    }

    @GetMapping("/pending")
    public List<Request> getPendingRequests() {
        return requestService.getPendingRequests();
    }

    @GetMapping("/{requestId}/debt-data-history")
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public List<DebtRequestData> getRequestDebtDataHistory(@PathVariable("requestId") String requestId) {
        return null;
    }

    @DeleteMapping("/{requestId}")
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public void deleteRequest(@PathVariable("requestId") String requestId) {
    }
}
