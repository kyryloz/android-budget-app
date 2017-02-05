package com.robotnec.budget.core.domain;

import lombok.Data;

/**
 * @author zak <zak@swingpulse.com>
 */
@Data
public class Currency implements Entity {
    private long id;
    private final String name;
}
