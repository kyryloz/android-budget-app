package com.robotnec.budget.core.domain.operation;

import com.robotnec.budget.core.domain.Account;

import lombok.Data;

/**
 * @author zak <zak@swingpulse.com>
 */
@Data
// example
public class Income implements Operation {

    private long date;
    private Account account;

    @Override
    public boolean execute(OperationReceiver receiver) {
        return receiver.receive(this);
    }
}
