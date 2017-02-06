package com.robotnec.budget.app.persistence.schema;

import com.yahoo.squidb.annotations.PrimaryKey;
import com.yahoo.squidb.annotations.TableModelSpec;

/**
 * @author zak <zak@swingpulse.com>
 */
@TableModelSpec(
        className = "MoneyOperationRecord",
        tableName = "moneyOperation",
        tableConstraint = "FOREIGN KEY (accountId) REFERENCES account(id)," +
                "FOREIGN KEY (categoryId) REFERENCES category(id)"
)
public class MoneyOperationSchema {

    @PrimaryKey
    private long id;

    private long accountId;
    private long categoryId;
    private String amount;
    private long date;
    private long currencyId;
}
