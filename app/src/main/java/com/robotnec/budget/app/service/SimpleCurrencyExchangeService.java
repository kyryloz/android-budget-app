package com.robotnec.budget.app.service;

import com.robotnec.budget.core.domain.Currency;
import com.robotnec.budget.core.domain.MoneyAmount;
import com.robotnec.budget.core.service.CurrencyExchangeService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zak <zak@swingpulse.com>
 */
public class SimpleCurrencyExchangeService implements CurrencyExchangeService {

    private static final Map<Currency, Double> exchangeRates = new HashMap<>();

    static {
        exchangeRates.put(Currency.UAH, 27.0);
    }

    @Override
    public MoneyAmount exchange(MoneyAmount from, Currency to) {
        Currency fromCurrency = from.getCurrency();
        if (fromCurrency.equals(to)) {
            return from;
        }
        if (fromCurrency.equals(Currency.USD) && to.equals(Currency.UAH)) {
            BigDecimal multiplier = BigDecimal.valueOf(exchangeRates.get(Currency.UAH));
            BigDecimal result = from.operations().multiply(multiplier);
            return new MoneyAmount(result, from.getCurrency());
        }
        if (fromCurrency.equals(Currency.UAH) && to.equals(Currency.USD)) {
            BigDecimal divisor = BigDecimal.valueOf(exchangeRates.get(Currency.UAH));
            BigDecimal result = from.operations().divide(divisor, RoundingMode.CEILING);
            return new MoneyAmount(result, from.getCurrency());
        }
        throw new UnsupportedOperationException("Can't exchange " + from + " to " + to);
    }
}
