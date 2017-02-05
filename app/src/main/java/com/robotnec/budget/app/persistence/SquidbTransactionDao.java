package com.robotnec.budget.app.persistence;

import com.robotnec.budget.app.persistence.schema.TransactionRecord;
import com.robotnec.budget.app.util.Mapper;
import com.robotnec.budget.core.dao.AccountDao;
import com.robotnec.budget.core.dao.CategoryDao;
import com.robotnec.budget.core.dao.TransactionDao;
import com.robotnec.budget.core.domain.Account;
import com.robotnec.budget.core.domain.Currency;
import com.robotnec.budget.core.domain.Transaction;
import com.yahoo.squidb.data.SquidCursor;
import com.yahoo.squidb.sql.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zak <zak@swingpulse.com>
 */

public class SquidbTransactionDao implements TransactionDao {

    private final BudgetDatabase database;
    private final AccountDao accountDao;
    private final CategoryDao categoryDao;

    public SquidbTransactionDao(BudgetDatabase database,
                                AccountDao accountDao,
                                CategoryDao categoryDao) {
        this.database = database;
        this.accountDao = accountDao;
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return getTransactionsByQuery(Query.select());
    }

    @Override
    public boolean addTransaction(Transaction transaction) {
        boolean success = false;
        database.beginTransaction();
        try {
            Account account = transaction.getAccount();
            account.setAmount(account.getAmount().add(transaction.getAmount()));
            accountDao.updateAccount(account);
            TransactionRecord record = Mapper.toRecord(transaction);
            success = database.persist(record);
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
        return success;
    }

    @Override
    public List<Transaction> getTransactionsForCategory(long categoryId) {
        Query query = Query.select().where(TransactionRecord.CATEGORY_ID.eq(categoryId));
        return getTransactionsByQuery(query);
    }

    private List<Transaction> getTransactionsByQuery(Query query) {
        SquidCursor<TransactionRecord> cursor = database.query(TransactionRecord.class, query);
        try {
            if (cursor.moveToFirst()) {
                List<TransactionRecord> result = new ArrayList<>();
                do {
                    TransactionRecord record = new TransactionRecord();
                    record.readPropertiesFromCursor(cursor);
                    result.add(record);
                } while (cursor.moveToNext());
                return Mapper.fromTransactionRecords(result,
                        accountDao::findById,
                        categoryDao::findById,
                        id -> new Currency("UAH"));
            }
        } finally {
            cursor.close();
        }
        return Collections.emptyList();
    }
}
