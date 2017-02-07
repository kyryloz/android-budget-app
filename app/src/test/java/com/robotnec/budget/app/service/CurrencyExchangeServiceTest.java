package com.robotnec.budget.app.service;

import com.robotnec.budget.core.domain.Currency;
import com.robotnec.budget.core.domain.MoneyAmount;
import com.robotnec.budget.core.service.CurrencyExchangeService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author zak <zak@swingpulse.com>
 */
public class CurrencyExchangeServiceTest {

    private CurrencyExchangeService currencyExchangeService;

    @Before
    public void before() throws Exception {
        currencyExchangeService = new SimpleCurrencyExchangeService();
    }

    @Test
    public void exchange() throws Exception {
        MoneyAmount from = MoneyAmount.of(1, Currency.USD);
        MoneyAmount actual = currencyExchangeService.exchange(from, Currency.UAH);
        Assert.assertEquals(MoneyAmount.of(27.0, Currency.UAH), actual);
    }

}