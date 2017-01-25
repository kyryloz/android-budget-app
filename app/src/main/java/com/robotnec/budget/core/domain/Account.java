package com.robotnec.budget.core.domain;

import java.io.Serializable;

/**
 * @author zak <zak@swingpulse.com>
 */
public class Account implements Serializable, Entity {

    private long id;
    private String name;
    private long amount;
    private String currencyCode;

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

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
