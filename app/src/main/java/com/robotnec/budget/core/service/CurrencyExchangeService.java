package com.robotnec.budget.core.service;

import com.robotnec.budget.core.domain.Currency;

import java.math.BigDecimal;

/**
 * @author zak <zak@swingpulse.com>
 */

public interface CurrencyExchangeService {
    BigDecimal exchange(Currency from, Currency to, BigDecimal amount);
}
