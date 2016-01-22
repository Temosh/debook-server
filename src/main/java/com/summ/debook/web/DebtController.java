package com.summ.debook.web;

import com.summ.debook.entity.LocalDebtEntity;
import com.summ.debook.service.LocalDebtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
@RestController
@RequestMapping("/debt")
public class DebtController {

    @Autowired
    private LocalDebtService localDebtService;

    @RequestMapping(value = "/local", method = RequestMethod.GET)
    public List<LocalDebtEntity> getDebts() {
        return localDebtService.getDebts();
    }
}
