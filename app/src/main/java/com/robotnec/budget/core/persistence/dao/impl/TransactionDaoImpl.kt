package com.robotnec.budget.core.persistence.dao.impl

import com.robotnec.budget.app.util.Mapper
import com.robotnec.budget.core.domain.operation.Transaction
import com.robotnec.budget.core.persistence.BudgetDatabase
import com.robotnec.budget.core.persistence.dao.AccountDao
import com.robotnec.budget.core.persistence.dao.CategoryDao
import com.robotnec.budget.core.persistence.dao.TransactionDao
import com.robotnec.budget.core.persistence.schema.TransactionRecord
import com.yahoo.squidb.data.SquidCursor
import com.yahoo.squidb.sql.Property
import com.yahoo.squidb.sql.Query
import java.util.ArrayList

/**
 * @author zak zak@swingpulse.com>
 */
class TransactionDaoImpl(database: BudgetDatabase,
                         private val accountDao: AccountDao,
                         private val categoryDao: CategoryDao)
    : SquidbDaoTemplate<Transaction, TransactionRecord>(database), TransactionDao {

    override fun getTransactionsForCategory(categoryId: Long): List<Transaction> {
        val query = Query.select().where(TransactionRecord.CATEGORY_ID.eq(categoryId))
        val cursor = database.query(TransactionRecord::class.java, query)
        try {
            if (cursor.moveToFirst()) {
                val result = ArrayList<TransactionRecord>()
                do {
                    result.add(fromCursor(cursor))
                } while (cursor.moveToNext())
                return map(result)
            }
        } finally {
            cursor.close()
        }
        return emptyList()
    }

    override fun fromCursor(cursor: SquidCursor<TransactionRecord>): TransactionRecord {
        val record = TransactionRecord()
        record.readPropertiesFromCursor(cursor)
        return record
    }

    override val recordClass: Class<TransactionRecord>
        get() = TransactionRecord::class.java

    override fun map(tableModels: List<TransactionRecord>): List<Transaction> {
        return Mapper.fromTransactionRecords(tableModels,
                { accountDao.findById(it) },
                { categoryDao.findById(it) })
    }

    override fun map(item: Transaction): TransactionRecord {
        return Mapper.toRecord(item)
    }

    override fun map(record: TransactionRecord): Transaction {
        return Mapper.fromRecord(record,
                { accountDao.findById(it) },
                { categoryDao.findById(it) })
    }

    override val idProperty: Property.LongProperty
        get() = TransactionRecord.ID
}
