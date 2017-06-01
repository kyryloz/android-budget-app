package com.robotnec.budget.core.persistence.dao.impl;

import com.robotnec.budget.app.util.Mapper;
import com.robotnec.budget.core.domain.Category;
import com.robotnec.budget.core.persistence.BudgetDatabase;
import com.robotnec.budget.core.persistence.dao.CategoryDao;
import com.robotnec.budget.core.persistence.schema.CategoryRecord;
import com.yahoo.squidb.data.SquidCursor;
import com.yahoo.squidb.sql.Property;

import java.util.List;

/**
 * @author zak <zak@swingpulse.com>
 */

public class CategoryDaoImpl extends SquidbDaoTemplate<Category, CategoryRecord> implements CategoryDao {

    public CategoryDaoImpl(BudgetDatabase database) {
        super(database);
    }

    @Override
    CategoryRecord fromCursor(SquidCursor<CategoryRecord> cursor) {
        CategoryRecord record = new CategoryRecord();
        record.readPropertiesFromCursor(cursor);
        return record;
    }

    @Override
    Class<CategoryRecord> getRecordClass() {
        return CategoryRecord.class;
    }

    @Override
    List<Category> map(List<CategoryRecord> tableModels) {
        return Mapper.INSTANCE.fromCategoryRecords(tableModels);
    }

    @Override
    CategoryRecord map(Category item) {
        return Mapper.INSTANCE.toRecord(item);
    }

    @Override
    Category map(CategoryRecord record) {
        return Mapper.INSTANCE.fromRecord(record);
    }

    @Override
    Property.LongProperty getIdProperty() {
        return CategoryRecord.ID;
    }
}
