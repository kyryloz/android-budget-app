package com.robotnec.budget.core.dao;

import com.robotnec.budget.core.domain.Currency;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface CurrencyDao {
    Currency findById(long id);
}
