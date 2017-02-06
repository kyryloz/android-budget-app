package com.robotnec.budget.core.domain;


import lombok.Data;

/**
 * @author zak <zak@swingpulse.com>
 */
@Data
public class Transaction {
    private long id;
    private Account account;
    private Category category;
    private MoneyAmount amount;
    private long date;
}
