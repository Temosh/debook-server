package com.summ.debook.service;

import com.summ.debook.dao.CreditTypeDao;
import com.summ.debook.dao.CurrencyDao;
import com.summ.debook.dao.DebtDao;
import com.summ.debook.dao.PersonDao;
import com.summ.debook.entity.CurrencyEntity;
import com.summ.debook.entity.DebtEntity;
import com.summ.debook.entity.PersonEntity;
import com.summ.debook.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    private UserService userService;

    @Transactional
    @Override
    public DebtEntity createDebt(DebtEntity debtEntity, Long personId) {
        UserEntity currentUser = userService.getCurrentUser();
        PersonEntity personEntity = personDao.find(personId);

        //TODO Throw proper exception
        if (!personEntity.getOwnerUser().getUserId().equals(currentUser.getUserId())) {
            throw new IllegalArgumentException();
        }

        debtEntity.setPerson(personEntity);
        debtEntity.setCurrency(currencyDao.find(debtEntity.getCurrency().getId()));
        debtEntity.setCreditType(creditTypeDao.findByType(debtEntity.getCreditType().getType()));

        if (LOG.isInfoEnabled()) LOG.info("Creating new debt: " + debtEntity); //TODO TEMP
        debtDao.create(debtEntity);
        return debtEntity;
    }

    @Transactional
    @Override
    public DebtEntity updateDebt(DebtEntity partialDebtEntity, Long personId, Long currencyId) {
        UserEntity currentUser = userService.getCurrentUser();
        PersonEntity personEntity = personDao.find(personId);

        //TODO Throw proper exception
        if (!personEntity.getOwnerUser().getUserId().equals(currentUser.getUserId())) {
            throw new IllegalArgumentException();
        }

        CurrencyEntity currencyEntity = currencyDao.find(currencyId);

        DebtEntity debtEntity = debtDao.find(personEntity, currencyEntity);

        debtEntity.setCreditType(creditTypeDao.findByType(partialDebtEntity.getCreditType().getType()));
        debtEntity.setValue(partialDebtEntity.getValue());

        if (LOG.isInfoEnabled()) LOG.info("Updating debt: " + debtEntity); //TODO TEMP
        debtDao.edit(debtEntity);
        return debtEntity;
    }
}
