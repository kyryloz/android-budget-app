package com.robotnec.budget.app.persistence.schema;

import com.yahoo.squidb.annotations.ColumnSpec;
import com.yahoo.squidb.annotations.PrimaryKey;
import com.yahoo.squidb.annotations.TableModelSpec;

@TableModelSpec(className = "AccountRecord", tableName = "account")
public class AccountSchema {

    @PrimaryKey
    private long id;

    @ColumnSpec(constraints = "NOT NULL UNIQUE")
    private String name;

    private String amount;

    private long currencyId;
}