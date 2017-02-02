package com.robotnec.budget.core.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * @author zak <zak@swingpulse.com>
 */
@Data
public class Account implements Serializable, Entity {

    private long id;
    private String name;
    private BigDecimal amount;
    private long currencyId;
}
