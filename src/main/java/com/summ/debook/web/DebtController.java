package com.summ.debook.web;

import com.summ.debook.entity.DebtEntity;
import com.summ.debook.service.DebtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
@RestController
@RequestMapping("/person/{personId}/debt")
public class DebtController {

    private static Logger LOG = LoggerFactory.getLogger(DebtController.class);

    @Autowired
    private DebtService debtService;

    @PostMapping
    public DebtEntity createDebt(@RequestBody DebtEntity debtEntity, @PathVariable("personId") Long personId) {
        return debtService.createDebt(debtEntity, personId);
    }

    @GetMapping("/{debtId}")
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public DebtEntity getDebt(@PathVariable String debtId) {
        return null;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public List<DebtEntity> getDebts() {
        return null;
    }

    //TODO Legacy mapping
    @PatchMapping("/{currencyId}")
    public DebtEntity updateDebt(
            @PathVariable("personId") Long personId,
            @PathVariable("currencyId") Long currencyId,
            @RequestBody DebtEntity partialDebtEntity) {
        return debtService.updateDebt(partialDebtEntity, personId, currencyId);
    }

    @DeleteMapping("/{debtId}")
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public void deleteDebt(@PathVariable String debtId) {
    }
}
