package com.robotnec.budget.core.service;

import com.robotnec.budget.core.domain.money.MoneyOperation;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface MoneyOperationService {
    boolean execute(MoneyOperation operation);
}
