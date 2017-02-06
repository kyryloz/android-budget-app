package com.robotnec.budget.core.domain;

import java.io.Serializable;

import lombok.Data;

/**
 * @author zak <zak@swingpulse.com>
 */
@Data
public class Account implements Serializable, Entity {
    private long id;
    private String name;
    private MoneyAmount amount;
}
