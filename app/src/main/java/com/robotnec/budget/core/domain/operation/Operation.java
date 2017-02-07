package com.robotnec.budget.core.domain.operation;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface Operation {
    boolean execute(OperationReceiver receiver);
}
