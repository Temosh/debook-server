package com.summ.debook.web;

import com.summ.debook.entity.CurrencyEntity;
import com.summ.debook.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Currency;
import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/{currencyId}")
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public Currency getCurrency(@PathVariable String currencyId) {
        return null;
    }

    @GetMapping()
    public List<CurrencyEntity> getCurrencies() {
        return currencyService.getCurrencies();
    }
}
