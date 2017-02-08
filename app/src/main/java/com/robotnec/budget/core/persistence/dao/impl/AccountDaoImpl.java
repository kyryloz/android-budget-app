package com.robotnec.budget.core.persistence.dao.impl;

import com.robotnec.budget.core.persistence.BudgetDatabase;
import com.robotnec.budget.core.persistence.schema.AccountRecord;
import com.robotnec.budget.app.util.Mapper;
import com.robotnec.budget.core.persistence.dao.AccountDao;
import com.robotnec.budget.core.domain.Account;
import com.yahoo.squidb.data.SquidCursor;
import com.yahoo.squidb.sql.Property;

import java.util.List;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AccountDaoImpl extends SquidbDaoTemplate<Account, AccountRecord> implements AccountDao {

    public AccountDaoImpl(BudgetDatabase database) {
        super(database);
    }

    @Override
    AccountRecord fromCursor(SquidCursor cursor) {
        AccountRecord record = new AccountRecord();
        record.readPropertiesFromCursor(cursor);
        return record;
    }

    @Override
    Class<AccountRecord> getRecordClass() {
        return AccountRecord.class;
    }

    @Override
    List<Account> map(List<AccountRecord> items) {
        return Mapper.fromAccountRecords(items);
    }

    @Override
    AccountRecord map(Account item) {
        return Mapper.toRecord(item);
    }

    @Override
    Account map(AccountRecord record) {
        return Mapper.fromRecord(record);
    }

    @Override
    Property.LongProperty getIdProperty() {
        return AccountRecord.ID;
    }
}
