package com.robotnec.budget.app.persistence;

import com.robotnec.budget.core.dao.BaseDao;
import com.robotnec.budget.core.domain.Identifiable;
import com.yahoo.squidb.data.SquidCursor;
import com.yahoo.squidb.data.TableModel;
import com.yahoo.squidb.sql.Property;
import com.yahoo.squidb.sql.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zak <zak@swingpulse.com>
 */
public abstract class SquidbDaoTemplate<R extends Identifiable, T extends TableModel> implements BaseDao<R> {

    BudgetDatabase database;

    public SquidbDaoTemplate(BudgetDatabase database) {
        this.database = database;
    }

    @Override
    public R findById(long id) {
        T item = database.fetchByCriterion(getRecordClass(), getIdProperty().eq(id));
        return map(item);
    }

    @Override
    public List<R> getAll() {
        SquidCursor<T> cursor = database.query(getRecordClass(), Query.select());
        try {
            if (cursor.moveToFirst()) {
                List<T> tableModels = new ArrayList<>();
                do {
                    tableModels.add(fromCursor(cursor));
                } while (cursor.moveToNext());
                return map(tableModels);
            }
        } finally {
            cursor.close();
        }
        return Collections.emptyList();
    }

    @Override
    public boolean remove(long id) {
        return database.deleteWhere(getRecordClass(), getIdProperty().eq(id)) != 0;
    }

    @Override
    public boolean createOrUpdate(R entity) {
        T record = map(entity);
        boolean success = database.persist(record);
        if (success) {
            entity.setId(record.getRowId());
        }
        return success;
    }

    abstract T fromCursor(SquidCursor<T> cursor);

    abstract Class<T> getRecordClass();

    abstract List<R> map(List<T> tableModels);

    abstract T map(R item);

    abstract R map(T record);

    abstract Property.LongProperty getIdProperty();
}
