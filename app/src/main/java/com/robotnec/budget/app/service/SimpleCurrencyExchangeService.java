package com.robotnec.budget.app.service;

import com.robotnec.budget.core.domain.Currency;
import com.robotnec.budget.core.service.CurrencyExchangeService;

import java.math.BigDecimal;
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
    public BigDecimal exchange(Currency from, Currency to, BigDecimal amount) {
        if (from.equals(to)) {
            return amount;
        }
        if (from.equals(Currency.USD) && to.equals(Currency.UAH)) {
            BigDecimal divisor = BigDecimal.valueOf(exchangeRates.get(Currency.UAH));
            return amount.divide(divisor, BigDecimal.ROUND_CEILING);
        }
        if (from.equals(Currency.UAH) && to.equals(Currency.USD)) {
            BigDecimal muliplicant = BigDecimal.valueOf(exchangeRates.get(Currency.UAH));
            return amount.multiply(muliplicant);
        }
        throw new UnsupportedOperationException("Can't exchange " + from + " to " + to);
    }
}
