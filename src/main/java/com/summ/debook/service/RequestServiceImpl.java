package com.summ.debook.service;

import com.summ.debook.dao.*;
import com.summ.debook.dto.DebtRequestData;
import com.summ.debook.dto.Request;
import com.summ.debook.entity.*;
import com.summ.debook.service.converter.IdConversionHelper;
import com.summ.debook.service.converter.RequestConverter;
import com.summ.debook.type.RequestType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

/**
 * @author Serhii Tymoshenko
 */
@Component
public class RequestServiceImpl implements RequestService {

    private static Logger LOG = LoggerFactory.getLogger(RequestServiceImpl.class);

    @Autowired
    private RequestDao requestDao;
    @Autowired
    private DebtRequestDataDao debtRequestDataDao;

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private PersonService personService;
    @Autowired
    private DebtService debtService;

    @Autowired
    private RequestConverter requestConverter;

    @Override
    @Transactional
    public void createRequest(Request request) {
        RequestEntity requestEntity = requestConverter.convertDtoToEntity(request);

        //TODO Use something like Strategy pattern
        processConnectionRequestUserLinkage(requestEntity, request.getUserId(), request.getPersonId());

        requestDao.create(requestEntity);
        requestEntity.getDebtRequestDataList().forEach(data -> debtRequestDataDao.create(data));
    }

    @Override
    @Transactional
    public void updateRequest(String requestId, Request request) {
        request.setId(requestId); //TODO Strange operation. Need to perform validation in controller
        Long requestIdLong = IdConversionHelper.parseId(requestId, "Wrong request ID format");
        RequestEntity requestEntity = requestDao.findPendingRequest(requestIdLong);

        if (requestEntity == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pending request with ID " + requestIdLong + " not found");
        }

        //TODO Use something like Strategy pattern
        processConnectionRequestUserLinkage(requestEntity, request.getUserId(), request.getPersonId());

        if (request.isRejected() != null && request.isRejected()) {
            rejectConnectionRequest(requestEntity);
        } else if (request.isProcessed() != null && request.isProcessed()) {
            acceptConnectionRequest(requestEntity);
        } else {
            modifyConnectionRequest(requestEntity, request);
        }

        requestEntity.setLastUpdater(authenticationService.getCurrentUser());
    }

    @Override
    @Transactional
    public Request getRequest(String requestId) {
        return requestConverter.convertEntityToDto(requestDao.find(IdConversionHelper.parseId(requestId, "Wrong request ID format")));
    }

    @Override
    @Transactional
    public List<Request> getRequests() {
        List<RequestEntity> requestEntityList = requestDao.findAll();
        List<Request> requestList = new ArrayList<>(requestEntityList.size());
        requestEntityList.forEach(requestEntity -> requestList.add(requestConverter.convertEntityToDto(requestEntity)));
        return requestList;
    }

    @Override
    @Transactional
    public List<Request> getPendingRequests() {
        List<RequestEntity> requestEntityList = requestDao.findAllPendingRequest();
        List<Request> requestList = new ArrayList<>(requestEntityList.size());
        requestEntityList.forEach(requestEntity -> requestList.add(requestConverter.convertEntityToDto(requestEntity)));
        return requestList;
    }

    @Override
    public List<DebtRequestData> getRequestDebtDataHistory(String requestId) {
        return null;
    }

    @Override
    public void deleteRequest(String requestId) {

    }

    private void modifyConnectionRequest(RequestEntity requestEntity, Request newRequest) {
        List<DebtRequestDataEntity> previousData = requestEntity.getDebtRequestDataList();
        previousData.forEach(dataEntity -> dataEntity.setProcessed(true));

        List<DebtRequestDataEntity> newData = requestConverter.convertDtoToEntity(newRequest).getDebtRequestDataList();
        newData.forEach(data -> debtRequestDataDao.create(data));
    }

    private void acceptConnectionRequest(RequestEntity requestEntity) {
        UserEntity currentUser = authenticationService.getCurrentUser();

        if (currentUser.getUserId().equals(requestEntity.getLastUpdater().getUserId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Last updater cannot accept request");
        }

        List<DebtRequestDataEntity> debtData = requestEntity.getDebtRequestDataList();

        //TODO Use something like Strategy pattern
        if (requestEntity.getType() == RequestType.CONNECTION) {
            personService.approveConnection(requestEntity.getSourceUser(), requestEntity.getTargetUser());
            debtData.forEach(dataEntity -> debtService.applyDebt(requestEntity.getSourceUser(), requestEntity.getTargetUser(), dataEntity));
        } else if (requestEntity.getType() == RequestType.DEBT) {
            debtData.forEach(dataEntity -> debtService.applyDebtAdjustment(requestEntity.getSourceUser(), requestEntity.getTargetUser(), dataEntity));
        }

        debtData.forEach(dataEntity -> dataEntity.setProcessed(true));

        requestEntity.setProcessed(true);
    }

    private void rejectConnectionRequest(RequestEntity requestEntity) {
        UserEntity currentUser = authenticationService.getCurrentUser();

        if (currentUser.getUserId().equals(requestEntity.getLastUpdater().getUserId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Last updater cannot reject request");
        }

        //TODO Use something like Strategy pattern
        if (requestEntity.getType() == RequestType.CONNECTION) {
            personService.removeConnection(requestEntity.getSourceUser(), requestEntity.getTargetUser());
        }

        List<DebtRequestDataEntity> data = requestEntity.getDebtRequestDataList();
        data.forEach(dataEntity -> dataEntity.setProcessed(true));

        requestEntity.setRejected(true);
        requestEntity.setProcessed(true);
    }

    //TODO Check case when client tries to remove linkage sending null as person ID
    private void processConnectionRequestUserLinkage(RequestEntity request, String userIdToConnect, String personIdToConnect) {
        UserEntity currentUser = authenticationService.getCurrentUser();

        if (userIdToConnect.equals(currentUser.getUserId().toString())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request User ID field should contain ID of another user account");
        }

        if (personIdToConnect != null && request.getType() == RequestType.CONNECTION) {
            if (currentUser.getUserId().equals(request.getSourceUser().getUserId())) {
                personService.connectUser(personIdToConnect, request.getTargetUser());
            } else if (currentUser.getUserId().equals(request.getTargetUser().getUserId())) {
                personService.connectUser(personIdToConnect, request.getSourceUser());
            } else {
                if (LOG.isErrorEnabled()) {
                    LOG.error("Wrong user ID: " + userIdToConnect);
                }
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }
    }
}
