package com.summ.debook.service;

import com.summ.debook.dao.CreditTypeDao;
import com.summ.debook.dao.CurrencyDao;
import com.summ.debook.dao.DebtDao;
import com.summ.debook.dao.PersonDao;
import com.summ.debook.entity.*;
import com.summ.debook.type.CreditType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
@Component
public class DebtServiceImpl implements DebtService {

    private static Logger LOG = LoggerFactory.getLogger(DebtServiceImpl.class);

    @Autowired
    private DebtDao debtDao;
    @Autowired
    private PersonDao personDao;
    @Autowired
    private CurrencyDao currencyDao;
    @Autowired
    private CreditTypeDao creditTypeDao;

    @Autowired
    private AuthenticationService authenticationService;

    @Transactional
    @Override
    public DebtEntity createDebt(DebtEntity debtEntity, long personId) {
        PersonEntity personEntity = personDao.find(personId);
        validateDebtOperationForPerson(personEntity);

        debtEntity.setPerson(personEntity);
        debtEntity.setCurrency(currencyDao.find(debtEntity.getCurrency().getId()));
        debtEntity.setCreditType(creditTypeDao.findByType(debtEntity.getCreditType().getType()));

        if (LOG.isInfoEnabled()) LOG.info("Creating new debt: " + debtEntity); //TODO TEMP
        debtDao.create(debtEntity);
        return debtEntity;
    }

    @Transactional
    @Override
    public DebtEntity updateDebt(DebtEntity partialDebtEntity, long personId, long currencyId) {
        PersonEntity personEntity = personDao.find(personId);
        validateDebtOperationForPerson(personEntity);

        CurrencyEntity currencyEntity = currencyDao.find(currencyId);
        DebtEntity debtEntity = debtDao.find(personEntity, currencyEntity);

        debtEntity.setCreditType(creditTypeDao.findByType(partialDebtEntity.getCreditType().getType()));
        debtEntity.setValue(partialDebtEntity.getValue());

        return debtEntity;
    }

    @Override
    public void applyDebt(UserEntity sourceUser, UserEntity targetUser, DebtRequestDataEntity debtRequestDataEntity) {
        applyDebt(sourceUser, targetUser, debtRequestDataEntity, false);
    }

    @Override
    public void applyDebtAdjustment(UserEntity sourceUser, UserEntity targetUser, DebtRequestDataEntity debtRequestDataEntity) {
        applyDebt(sourceUser, targetUser, debtRequestDataEntity, true);
    }

    private void applyDebt(UserEntity sourceUser, UserEntity targetUser, DebtRequestDataEntity debtRequestDataEntity, boolean isAdjustment) {
        List<PersonEntity> personList = personDao.findByConnection(sourceUser, targetUser);
        for (PersonEntity personEntity : personList) {
            CreditType creditType;
            if (personEntity.getOwnerUser().getUserId().equals(debtRequestDataEntity.getOwnerUser().getUserId())) {
                creditType = debtRequestDataEntity.getCreditType().getType();
            } else {
                if (debtRequestDataEntity.getCreditType().getType() == CreditType.LOAN) {
                    creditType = CreditType.DEBT;
                } else if (debtRequestDataEntity.getCreditType().getType() == CreditType.DEBT) {
                    creditType = CreditType.LOAN;
                } else {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Not valid credit type " + debtRequestDataEntity.getCreditType().getType());
                }
            }

            DebtEntity debtEntity = debtDao.find(personEntity, debtRequestDataEntity.getCurrency());
            if (debtEntity == null) {
                debtEntity = new DebtEntity();
                debtEntity.setPerson(personEntity);
                debtEntity.setCurrency(debtRequestDataEntity.getCurrency());
                applyDebt(debtEntity, creditType, debtRequestDataEntity.getValue());
                debtDao.create(debtEntity);
            } else {
                if (isAdjustment) {
                    applyDebtAdjustment(debtEntity, creditType, debtRequestDataEntity.getValue());
                } else {
                    applyDebt(debtEntity, creditType, debtRequestDataEntity.getValue());
                }
            }
        }
    }

    private void applyDebt(DebtEntity debtEntity, CreditType creditType, BigDecimal value) {
        debtEntity.setCreditType(creditTypeDao.findByType(creditType));
        debtEntity.setValue(value);
    }

    private void applyDebtAdjustment(DebtEntity debtEntity, CreditType creditType, BigDecimal valueAdjustment) {
        if (debtEntity.getCreditType().getType() == creditType) {
            debtEntity.setValue(debtEntity.getValue().add(valueAdjustment));
        } else {
            if (debtEntity.getValue().compareTo(valueAdjustment) >= 0) {
                debtEntity.setValue(debtEntity.getValue().subtract(valueAdjustment));
            } else {
                BigDecimal value = valueAdjustment.subtract(debtEntity.getValue());
                debtEntity.setCreditType(creditTypeDao.findByType(creditType));
                debtEntity.setValue(value);
            }
        }
    }

    private void validateDebtOperationForPerson(PersonEntity personEntity) {
        UserEntity currentUser = authenticationService.getCurrentUser();

        if (!personEntity.getOwnerUser().getUserId().equals(currentUser.getUserId())) {
            if (LOG.isWarnEnabled()) {
                LOG.warn("Client trying to create debt for person of another user: " + personEntity + ". Current user: " + currentUser);
            }
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        if (personEntity.getConnectedUser() != null) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Client trying to create debt for person with connection to user: " + personEntity + ". Current user: " + currentUser);
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Debts for persons with connection to another user cannot be updated directly. Use debt request instead.");
        }
    }
}
