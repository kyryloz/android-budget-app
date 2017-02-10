package com.robotnec.budget.core.domain.operation;

import com.robotnec.budget.core.domain.Account;
import com.robotnec.budget.core.domain.Category;
import com.robotnec.budget.core.domain.Identifiable;
import com.robotnec.budget.core.domain.MoneyAmount;

import lombok.Data;

/**
 * @author zak <zak@swingpulse.com>
 */
@Data
public class Transaction implements Identifiable {
    long id;
    MoneyAmount amount;
    long date;
    Account account;
    Category category;
}
