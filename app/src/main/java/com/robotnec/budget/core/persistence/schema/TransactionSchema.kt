package com.robotnec.budget.core.persistence.schema

import com.yahoo.squidb.annotations.PrimaryKey
import com.yahoo.squidb.annotations.TableModelSpec

/**
 * @author zak zak@swingpulse.com>
 */
@TableModelSpec(className = "TransactionRecord",
        tableName = "transactions",
        tableConstraint = "FOREIGN KEY (accountId) REFERENCES account(id)," + "FOREIGN KEY (categoryId) REFERENCES category(id)")
class TransactionSchema {

    @PrimaryKey
    private val id: Long = 0
    private val accountId: Long = 0
    private val categoryId: Long = 0
    private val amount: String? = null
    private val date: Long = 0
}
