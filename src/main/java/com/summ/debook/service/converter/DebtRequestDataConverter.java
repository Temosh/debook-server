package com.summ.debook.service.converter;

import com.summ.debook.dao.CreditTypeDao;
import com.summ.debook.dao.CurrencyDao;
import com.summ.debook.dto.Currency;
import com.summ.debook.dto.DebtRequestData;
import com.summ.debook.entity.DebtRequestDataEntity;
import com.summ.debook.service.UserService;
import com.summ.debook.type.CreditType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Serhii Tymoshenko
 */
@Component
class DebtRequestDataConverter implements Converter<DebtRequestData, DebtRequestDataEntity> {

    @Autowired
    private UserService userService;

    @Autowired
    private CurrencyDao currencyDao;

    @Autowired
    private CreditTypeDao creditTypeDao;

    @Override
    public DebtRequestDataEntity convertDtoToEntity(DebtRequestData data) {
        DebtRequestDataEntity debtRequestDataEntity = new DebtRequestDataEntity();
        debtRequestDataEntity.setOwnerUser(userService.getCurrentUser());
        debtRequestDataEntity.setCurrency(currencyDao.getReference(IdConversionHelper.parseId(data.getCurrency().getId(), "Wrong currency ID format")));
        debtRequestDataEntity.setCreditType(creditTypeDao.findByType(data.getCreditType()));
        debtRequestDataEntity.setValue(data.getValue());
        debtRequestDataEntity.setMessage(data.getMessage());
        debtRequestDataEntity.setProcessed(false);
        return debtRequestDataEntity;
    }

    @Override
    public DebtRequestData convertEntityToDto(DebtRequestDataEntity dataEntity) {
        Currency currency = new Currency();
        currency.setId(dataEntity.getCurrency().getId().toString());
        currency.setCode(dataEntity.getCurrency().getCode());
        currency.setSign(dataEntity.getCurrency().getSign());

        CreditType creditType;
        if (userService.getCurrentUser().getUserId().equals(dataEntity.getOwnerUser().getUserId())) {
            creditType = dataEntity.getCreditType().getType();
        } else {
            if (dataEntity.getCreditType().getType() == CreditType.LOAN) {
                creditType = CreditType.DEBT;
            } else if (dataEntity.getCreditType().getType() == CreditType.DEBT) {
                creditType = CreditType.LOAN;
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Not valid credit type " + dataEntity.getCreditType().getType());
            }
        }

        DebtRequestData request = new DebtRequestData();
        request.setUpdaterId(dataEntity.getOwnerUser().getUserId().toString());
        request.setCreditType(creditType);
        request.setCurrency(currency);
        request.setValue(dataEntity.getValue());
        request.setMessage(dataEntity.getMessage());
        request.setCreationTime(dataEntity.getCreateTime().toLocalDateTime());

        return request;
    }
}
