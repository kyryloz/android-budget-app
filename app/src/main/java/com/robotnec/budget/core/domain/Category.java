package com.robotnec.budget.core.domain;

import java.io.Serializable;

/**
 * @author zak <zak@swingpulse.com>
 */
public class Category implements Serializable, Entity {
    private long id;
    private String name;

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
