package com.robotnec.budget.core.persistence.dao.impl

import com.robotnec.budget.app.util.Mapper
import com.robotnec.budget.core.domain.Category
import com.robotnec.budget.core.persistence.BudgetDatabase
import com.robotnec.budget.core.persistence.dao.CategoryDao
import com.robotnec.budget.core.persistence.schema.CategoryRecord
import com.yahoo.squidb.data.SquidCursor
import com.yahoo.squidb.sql.Property

/**
 * @author zak zak@swingpulse.com>
 */

class CategoryDaoImpl(database: BudgetDatabase) : SquidbDaoTemplate<Category, CategoryRecord>(database), CategoryDao {

    override val recordClass: Class<CategoryRecord>
        get() = CategoryRecord::class.java

    override val idProperty: Property.LongProperty
        get() = CategoryRecord.ID

    override fun fromCursor(cursor: SquidCursor<CategoryRecord>): CategoryRecord {
        val record = CategoryRecord()
        record.readPropertiesFromCursor(cursor)
        return record
    }

    override fun map(tableModels: List<CategoryRecord>): List<Category> {
        return Mapper.fromCategoryRecords(tableModels)
    }

    override fun map(item: Category): CategoryRecord {
        return Mapper.toRecord(item)
    }

    override fun map(record: CategoryRecord): Category {
        return Mapper.fromRecord(record)
    }
}
