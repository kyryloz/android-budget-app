package com.robotnec.budget.core.service;

import com.robotnec.budget.core.domain.operation.Operation;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface MoneyOperationBroker {
    boolean execute(Operation operation);
}
