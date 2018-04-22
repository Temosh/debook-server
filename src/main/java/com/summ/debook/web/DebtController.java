package com.summ.debook.web;

import com.summ.debook.entity.DebtEntity;
import com.summ.debook.service.DebtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Serhii Tymoshenko
 */
@RestController
public class DebtController {

    private static Logger LOG = LoggerFactory.getLogger(DebtController.class);

    @Autowired
    private DebtService debtService;

    @RequestMapping(value = "/person/{personId}/debt", method = RequestMethod.POST)
    public DebtEntity createDebt(@RequestBody DebtEntity debtEntity, @PathVariable("personId") Long personId) {
        return debtService.createDebt(debtEntity, personId);
    }

    //TODO Should be mapped manually
    @RequestMapping(value = "/person/{personId}/debt/{currencyId}", method = RequestMethod.PATCH)
    public DebtEntity updateDebt(
            @PathVariable("personId") Long personId,
            @PathVariable("currencyId") Long currencyId,
            @RequestBody DebtEntity partialDebtEntity) {
        return debtService.updateDebt(partialDebtEntity, personId, currencyId);
    }
}
