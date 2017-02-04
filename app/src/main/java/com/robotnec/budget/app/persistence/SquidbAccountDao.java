package com.robotnec.budget.app.persistence;

import com.robotnec.budget.app.persistence.schema.AccountRecord;
import com.robotnec.budget.app.util.Mapper;
import com.robotnec.budget.core.dao.AccountDao;
import com.robotnec.budget.core.domain.Account;
import com.yahoo.squidb.data.SquidCursor;
import com.yahoo.squidb.sql.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zak <zak@swingpulse.com>
 */
public class SquidbAccountDao implements AccountDao {

    private final BudgetDatabase database;

    public SquidbAccountDao(BudgetDatabase database) {
        this.database = database;
    }

    @Override
    public List<Account> getAllAccounts() {
        SquidCursor<AccountRecord> cursor = database.query(AccountRecord.class, Query.select());
        try {
            if (cursor.moveToFirst()) {
                List<AccountRecord> result = new ArrayList<>();
                do {
                    AccountRecord record = new AccountRecord();
                    record.readPropertiesFromCursor(cursor);
                    result.add(record);
                } while (cursor.moveToNext());
                return Mapper.fromAccountRecords(result);
            }
        } finally {
            cursor.close();
        }
        return Collections.emptyList();
    }

    @Override
    public boolean addAccount(Account account) {
        return database.persist(Mapper.toRecord(account));
    }

    @Override
    public void updateAccount(Account account) {
        addAccount(account);
    }

    @Override
    public boolean removeAccount(long accountId) {
        return database.deleteWhere(AccountRecord.class, AccountRecord.ID.eq(accountId)) != 0;
    }

    @Override
    public Account findById(long accountId) {
        AccountRecord r = database.fetchByCriterion(AccountRecord.class, AccountRecord.ID.eq(accountId));
        return Mapper.fromRecord(r);
    }
}
