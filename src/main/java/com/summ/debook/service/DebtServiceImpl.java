package com.summ.debook.service;

import com.summ.debook.dao.CreditTypeDao;
import com.summ.debook.dao.CurrencyDao;
import com.summ.debook.dao.DebtDao;
import com.summ.debook.dao.PersonDao;
import com.summ.debook.entity.DebtEntity;
import com.summ.debook.entity.DebtIdEntity;
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
        debtEntity.setUser(userService.getCurrentUser());
        debtEntity.setPerson(personDao.find(personId));
        debtEntity.setCurrency(currencyDao.find(debtEntity.getCurrency().getId()));
        debtEntity.setCreditType(creditTypeDao.findByType(debtEntity.getCreditType().getType()));
        debtEntity.setId(new DebtIdEntity(
                debtEntity.getUser().getUserId(),
                debtEntity.getPerson().getPersonId(),
                debtEntity.getCurrency().getId()
        ));

        if (LOG.isInfoEnabled()) LOG.info("Creating new debt: " + debtEntity); //TODO TEMP
        debtDao.create(debtEntity);
        return debtEntity;
    }

    @Transactional
    @Override
    public DebtEntity updateDebt(DebtEntity partialDebtEntity, Long personId, Long currencyId) {
        DebtIdEntity debtId = new DebtIdEntity(
                userService.getCurrentUser().getUserId(),
                personId,
                currencyId
        );
        DebtEntity debtEntity = debtDao.find(debtId);

        debtEntity.setCreditType(creditTypeDao.findByType(partialDebtEntity.getCreditType().getType()));
        debtEntity.setValue(partialDebtEntity.getValue());

        if (LOG.isInfoEnabled()) LOG.info("Updating debt: " + debtEntity); //TODO TEMP
        debtDao.edit(debtEntity);
        return debtEntity;
    }
}
