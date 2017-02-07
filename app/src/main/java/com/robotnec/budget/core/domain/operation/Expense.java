package com.robotnec.budget.core.domain.operation;

import com.robotnec.budget.core.domain.Account;
import com.robotnec.budget.core.domain.Category;
import com.robotnec.budget.core.domain.MoneyAmount;

import lombok.Data;

/**
 * @author zak <zak@swingpulse.com>
 */
@Data
public class Expense implements Operation {

    private MoneyAmount amount;
    private long date;
    private Account account;
    private Category category;

    @Override
    public boolean execute(OperationReceiver receiver) {
        return receiver.receive(this);
    }
}
