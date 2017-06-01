package com.robotnec.budget.core.persistence.dao.impl

import com.robotnec.budget.app.util.Mapper
import com.robotnec.budget.core.domain.Account
import com.robotnec.budget.core.persistence.BudgetDatabase
import com.robotnec.budget.core.persistence.dao.AccountDao
import com.robotnec.budget.core.persistence.schema.AccountRecord
import com.yahoo.squidb.data.SquidCursor
import com.yahoo.squidb.sql.Property

/**
 * @author zak zak@swingpulse.com>
 */
class AccountDaoImpl(database: BudgetDatabase) : SquidbDaoTemplate<Account, AccountRecord>(database), AccountDao {

    override val recordClass: Class<AccountRecord>
        get() = AccountRecord::class.java

    override val idProperty: Property.LongProperty
        get() = AccountRecord.ID

    override fun fromCursor(cursor: SquidCursor<AccountRecord>): AccountRecord {
        val record = AccountRecord()
        record.readPropertiesFromCursor(cursor)
        return record
    }

    override fun map(tableModels: List<AccountRecord>): List<Account> {
        return Mapper.fromAccountRecords(tableModels)
    }

    override fun map(item: Account): AccountRecord {
        return Mapper.toRecord(item)
    }

    override fun map(record: AccountRecord): Account {
        return Mapper.fromRecord(record)
    }
}
