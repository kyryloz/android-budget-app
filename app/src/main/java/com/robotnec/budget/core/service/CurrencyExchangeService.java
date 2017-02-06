package com.robotnec.budget.core.service;

import com.robotnec.budget.core.domain.Currency;
import com.robotnec.budget.core.domain.MoneyAmount;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface CurrencyExchangeService {
    MoneyAmount exchange(MoneyAmount from, Currency to);
}
