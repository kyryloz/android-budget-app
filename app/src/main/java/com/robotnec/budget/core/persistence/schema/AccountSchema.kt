package com.robotnec.budget.core.persistence.schema

import com.yahoo.squidb.annotations.ColumnSpec
import com.yahoo.squidb.annotations.PrimaryKey
import com.yahoo.squidb.annotations.TableModelSpec

@TableModelSpec(className = "AccountRecord", tableName = "account")
class AccountSchema {

    @PrimaryKey
    private val id: Long = 0

    @ColumnSpec(constraints = "NOT NULL UNIQUE")
    private val name: String? = null

    private val amount: String? = null
}