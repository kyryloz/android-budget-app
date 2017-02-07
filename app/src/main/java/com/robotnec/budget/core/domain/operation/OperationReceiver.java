package com.robotnec.budget.core.domain.operation;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface OperationReceiver {
    boolean receive(Expense expense);
}
