package com.robotnec.budget.core.domain;


import java.math.BigDecimal;

import lombok.Data;

/**
 * @author zak <zak@swingpulse.com>
 */
@Data
public class Transaction {

    private long id;
    private Account account;
    private Category category;
    private BigDecimal amount;
    private long date;
    private Currency currency;
}
