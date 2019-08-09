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

//    @Autowired
//    private RequestService requestService;

    @PostMapping
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public void createRequest(@RequestBody Request request) {
    }

    @PutMapping("/{requestId}")
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public void updateRequest(@RequestBody Request request, @PathVariable("requestId") Long requestId) {
    }

    @GetMapping("/{requestId}")
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public Request getRequest(@PathVariable("requestId") Long requestId) {
        return null;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public List<Request> getRequests() {
        return null;
    }

    @GetMapping("/{requestId}/debt-data-history")
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public List<DebtRequestData> getRequestDebtDataHistory(@PathVariable("requestId") Long requestId) {
        return null;
    }

    @DeleteMapping("/{requestId}")
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public void deleteRequest(@PathVariable("requestId") Long requestId) {
    }
}
