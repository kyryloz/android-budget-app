package com.robotnec.budget.app.persistence;

import com.robotnec.budget.app.persistence.schema.CategoryRecord;
import com.robotnec.budget.app.util.Mapper;
import com.robotnec.budget.core.dao.CategoryDao;
import com.robotnec.budget.core.domain.Category;
import com.yahoo.squidb.data.SquidCursor;
import com.yahoo.squidb.sql.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zak <zak@swingpulse.com>
 */

public class SquidbCategoryDao implements CategoryDao {

    private final BudgetDatabase database;

    public SquidbCategoryDao(BudgetDatabase database) {
        this.database = database;
    }

    @Override
    public List<Category> getAllCategories() {
        SquidCursor<CategoryRecord> cursor = database.query(CategoryRecord.class, Query.select());
        try {
            if (cursor.moveToFirst()) {
                List<CategoryRecord> result = new ArrayList<>();
                do {
                    CategoryRecord record = new CategoryRecord();
                    record.readPropertiesFromCursor(cursor);
                    result.add(record);
                } while (cursor.moveToNext());
                return Mapper.fromCategoryRecords(result);
            }
        } finally {
            cursor.close();
        }
        return Collections.emptyList();
    }

    @Override
    public boolean addCategory(Category category) {
        return database.persist(Mapper.toRecord(category));
    }

    @Override
    public void updateCategory(Category category) {
        addCategory(category);
    }

    @Override
    public boolean removeCategory(long id) {
        return database.deleteWhere(CategoryRecord.class, CategoryRecord.ID.eq(id)) != 0;
    }
}
