package com.robotnec.budget.app.persistence;

import com.robotnec.budget.app.persistence.schema.CategoryRecord;
import com.robotnec.budget.app.util.Mapper;
import com.robotnec.budget.core.dao.CategoryDao;
import com.robotnec.budget.core.domain.Category;
import com.yahoo.squidb.data.SquidCursor;
import com.yahoo.squidb.sql.Property;
import com.yahoo.squidb.sql.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zak <zak@swingpulse.com>
 */

public class SquidbCategoryDao extends SquidbDaoTemplate<Category, CategoryRecord> implements CategoryDao {

    public SquidbCategoryDao(BudgetDatabase database) {
        super(database);
    }

    @Override
    public Category findById(long id) {
        CategoryRecord record = database.fetchByQuery(CategoryRecord.class, Query.select());
        return Mapper.fromRecord(record);
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
        return Mapper.fromCategoryRecords(tableModels);
    }

    @Override
    CategoryRecord map(Category item) {
        return Mapper.toRecord(item);
    }

    @Override
    Category map(CategoryRecord record) {
        return Mapper.fromRecord(record);
    }

    @Override
    Property.LongProperty getIdProperty() {
        return CategoryRecord.ID;
    }
}
