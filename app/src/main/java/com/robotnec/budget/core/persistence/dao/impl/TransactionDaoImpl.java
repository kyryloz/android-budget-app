package com.robotnec.budget.core.persistence.dao.impl;

import com.robotnec.budget.app.util.Mapper;
import com.robotnec.budget.core.domain.operation.Transaction;
import com.robotnec.budget.core.persistence.BudgetDatabase;
import com.robotnec.budget.core.persistence.dao.AccountDao;
import com.robotnec.budget.core.persistence.dao.CategoryDao;
import com.robotnec.budget.core.persistence.dao.TransactionDao;
import com.robotnec.budget.core.persistence.schema.TransactionRecord;
import com.yahoo.squidb.data.SquidCursor;
import com.yahoo.squidb.sql.Property;
import com.yahoo.squidb.sql.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zak <zak@swingpulse.com>
 */
public class TransactionDaoImpl extends SquidbDaoTemplate<Transaction, TransactionRecord>
        implements TransactionDao {

    private final AccountDao accountDao;
    private final CategoryDao categoryDao;

    public TransactionDaoImpl(BudgetDatabase database,
                              AccountDao accountDao,
                              CategoryDao categoryDao) {
        super(database);
        this.accountDao = accountDao;
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Transaction> getTransactionsForCategory(long categoryId) {
        Query query = Query.select().where(TransactionRecord.CATEGORY_ID.eq(categoryId));
        SquidCursor<TransactionRecord> cursor = database.query(TransactionRecord.class, query);
        try {
            if (cursor.moveToFirst()) {
                List<TransactionRecord> result = new ArrayList<>();
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
    TransactionRecord fromCursor(SquidCursor<TransactionRecord> cursor) {
        TransactionRecord record = new TransactionRecord();
        record.readPropertiesFromCursor(cursor);
        return record;
    }

    @Override
    Class<TransactionRecord> getRecordClass() {
        return TransactionRecord.class;
    }

    @Override
    List<Transaction> map(List<TransactionRecord> tableModels) {
        return Mapper.fromTransactionRecords(tableModels,
                accountDao::findById,
                categoryDao::findById);
    }

    @Override
    TransactionRecord map(Transaction item) {
        return Mapper.toRecord(item);
    }

    @Override
    Transaction map(TransactionRecord record) {
        return Mapper.fromRecord(record,
                accountDao::findById,
                categoryDao::findById);
    }

    @Override
    Property.LongProperty getIdProperty() {
        return TransactionRecord.ID;
    }
}
