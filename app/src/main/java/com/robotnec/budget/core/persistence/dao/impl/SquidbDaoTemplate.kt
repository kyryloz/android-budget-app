package com.robotnec.budget.core.persistence.dao.impl

import com.robotnec.budget.core.persistence.BudgetDatabase
import com.robotnec.budget.core.domain.Identifiable
import com.robotnec.budget.core.persistence.dao.BaseDao
import com.yahoo.squidb.data.SquidCursor
import com.yahoo.squidb.data.TableModel
import com.yahoo.squidb.sql.Property
import com.yahoo.squidb.sql.Query

import java.util.ArrayList
import java.util.Collections

/**
 * @author zak zak@swingpulse.com>
 */
abstract class SquidbDaoTemplate<R : Identifiable, T : TableModel>(
        internal var database: BudgetDatabase) : BaseDao<R> {

    override fun findById(id: Long): R {
        val item = database.fetchByCriterion(recordClass, idProperty.eq(id))
        return map(item)
    }

    override val all: List<R>
        get() {
            val cursor = database.query(recordClass, Query.select())
            try {
                if (cursor.moveToFirst()) {
                    val tableModels = ArrayList<T>()
                    do {
                        tableModels.add(fromCursor(cursor))
                    } while (cursor.moveToNext())
                    return map(tableModels)
                }
            } finally {
                cursor.close()
            }
            return emptyList()
        }

    override fun remove(id: Long): Boolean {
        return database.deleteWhere(recordClass, idProperty.eq(id)) != 0
    }

    override fun createOrUpdate(entity: R): Boolean {
        val record = map(entity)
        val success = database.persist(record)
        if (success) {
            entity.id = record.rowId
        }
        return success
    }

    internal abstract val recordClass: Class<T>

    internal abstract val idProperty: Property.LongProperty

    internal abstract fun fromCursor(cursor: SquidCursor<T>): T

    internal abstract fun map(tableModels: List<T>): List<R>

    internal abstract fun map(item: R): T

    internal abstract fun map(record: T): R
}
