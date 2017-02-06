package com.robotnec.budget.app.persistence;

import com.robotnec.budget.app.persistence.schema.MoneyOperationRecord;
import com.robotnec.budget.app.util.Mapper;
import com.robotnec.budget.core.dao.AccountDao;
import com.robotnec.budget.core.dao.CategoryDao;
import com.robotnec.budget.core.dao.MoneyOperationDao;
import com.robotnec.budget.core.domain.money.MoneyOperation;
import com.yahoo.squidb.data.SquidCursor;
import com.yahoo.squidb.sql.Property;
import com.yahoo.squidb.sql.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zak <zak@swingpulse.com>
 */
public class MoneyOperationDaoImpl extends SquidbDaoTemplate<MoneyOperation, MoneyOperationRecord>
        implements MoneyOperationDao {

    private final AccountDao accountDao;
    private final CategoryDao categoryDao;

    public MoneyOperationDaoImpl(BudgetDatabase database,
                                 AccountDao accountDao,
                                 CategoryDao categoryDao) {
        super(database);
        this.accountDao = accountDao;
        this.categoryDao = categoryDao;
    }

    @Override
    public List<MoneyOperation> getTransactionsForCategory(long categoryId) {
        Query query = Query.select().where(MoneyOperationRecord.CATEGORY_ID.eq(categoryId));
        SquidCursor<MoneyOperationRecord> cursor = database.query(MoneyOperationRecord.class, query);
        try {
            if (cursor.moveToFirst()) {
                List<MoneyOperationRecord> result = new ArrayList<>();
                do {
                    result.add(fromCursor(cursor));
                } while (cursor.moveToNext());
                return map(result);
            }
        } finally {
            cursor.close();
        }
        return Collections.emptyList();
    }

    @Override
    MoneyOperationRecord fromCursor(SquidCursor<MoneyOperationRecord> cursor) {
        MoneyOperationRecord record = new MoneyOperationRecord();
        record.readPropertiesFromCursor(cursor);
        return record;
    }

    @Override
    Class<MoneyOperationRecord> getRecordClass() {
        return MoneyOperationRecord.class;
    }

    @Override
    List<MoneyOperation> map(List<MoneyOperationRecord> tableModels) {
        return Mapper.fromTransactionRecords(tableModels,
                accountDao::findById,
                categoryDao::findById);
    }

    @Override
    MoneyOperationRecord map(MoneyOperation item) {
        return Mapper.toRecord(item);
    }

    @Override
    MoneyOperation map(MoneyOperationRecord record) {
        return Mapper.fromRecord(record,
                accountDao::findById,
                categoryDao::findById);
    }

    @Override
    Property.LongProperty getIdProperty() {
        return MoneyOperationRecord.ID;
    }
}
