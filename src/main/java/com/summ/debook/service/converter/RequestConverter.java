package com.summ.debook.service.converter;

import com.summ.debook.dao.RequestDao;
import com.summ.debook.dao.UserDao;
import com.summ.debook.dto.DebtRequestData;
import com.summ.debook.dto.Request;
import com.summ.debook.dto.User;
import com.summ.debook.entity.DebtRequestDataEntity;
import com.summ.debook.entity.PersonEntity;
import com.summ.debook.entity.RequestEntity;
import com.summ.debook.entity.UserEntity;
import com.summ.debook.service.AuthenticationService;
import com.summ.debook.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
@Component
public class RequestConverter implements Converter<Request, RequestEntity> {

    private static Logger LOG = LoggerFactory.getLogger(RequestConverter.class);

    @Autowired
    private RequestDao requestDao;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private PersonService personService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private Converter<DebtRequestData, DebtRequestDataEntity> debtRequestDataConverter;

    @Override
    public RequestEntity convertDtoToEntity(Request request) {
        UserEntity targetUser = userDao.getReference(IdConversionHelper.parseId(request.getUser().getId(), "Wrong target user ID format"));
        UserEntity currentUser = authenticationService.getCurrentUser();

        //TODO Validation in converter class
        if (currentUser.getUserId().equals(targetUser.getUserId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong target user ID: " + targetUser);
        }

        RequestEntity requestEntity;
        if (request.getId() == null) {
            requestEntity = new RequestEntity();
        } else {
            requestEntity = requestDao.getReference(IdConversionHelper.parseId(request.getId(), "Wrong request ID format"));
        }

        requestEntity.setType(request.getType());
        requestEntity.setSourceUser(currentUser);
        requestEntity.setTargetUser(targetUser);
        requestEntity.setMessage(request.getMessage());
        requestEntity.setProcessed(false);
        requestEntity.setLastUpdater(currentUser);

        List<DebtRequestDataEntity> dataList = new ArrayList<>();

        for (DebtRequestData data : request.getData()) {
            DebtRequestDataEntity dataEntity = debtRequestDataConverter.convertDtoToEntity(data);
            dataEntity.setRequest(requestEntity);
            dataList.add(dataEntity);
        }

        requestEntity.setDebtRequestDataList(dataList);

        return requestEntity;
    }

    @Override
    public Request convertEntityToDto(RequestEntity requestEntity) {
        UserEntity currentUser = authenticationService.getCurrentUser();

        PersonEntity personEntity;
        if (requestEntity.getSourceUser().getUserId().equals(currentUser.getUserId())) {
            personEntity = personService.getPersonByConnectedUser(requestEntity.getTargetUser());
        } else {
            personEntity = personService.getPersonByConnectedUser(requestEntity.getSourceUser());
        }

        Request request = new Request();
        request.setId(requestEntity.getId().toString());
        request.setType(requestEntity.getType());
        request.setUser(
                requestEntity.getSourceUser().getUserId().equals(currentUser.getUserId()) ?
                        convertUserEntityToDto(requestEntity.getTargetUser()) :
                        convertUserEntityToDto(requestEntity.getSourceUser())
        );
        request.setPersonId(personEntity == null ? null : personEntity.getPersonId().toString());
        request.setProcessed(requestEntity.isProcessed());
        request.setRejected(requestEntity.isRejected());
        request.setRejectMessage(requestEntity.getRejectMessage());
        request.setMessage(requestEntity.getMessage());
        request.setLastUpdaterId(requestEntity.getLastUpdater().getUserId().toString());

        List<DebtRequestData> dataList = new ArrayList<>();
        for (DebtRequestDataEntity dataEntity : requestEntity.getDebtRequestDataList()) {
            DebtRequestData data = debtRequestDataConverter.convertEntityToDto(dataEntity);
            dataList.add(data);
        }

        request.setData(dataList);

        return request;
    }

    private User convertUserEntityToDto(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getUserId().toString());
        user.setName(userEntity.getLogin());
        user.setAvatarUrl(null);
        return user;
    }
}
