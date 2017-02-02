package com.robotnec.budget.app.persistence;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.robotnec.budget.app.persistence.schema.AccountRecord;
import com.robotnec.budget.core.dao.AccountDao;
import com.robotnec.budget.core.domain.Account;
import com.yahoo.squidb.data.SquidCursor;
import com.yahoo.squidb.sql.Query;

import java.math.BigDecimal;
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
                return Stream.of(result)
                        .map(r -> {
                            Account account = new Account();
                            account.setAmount(new BigDecimal(r.getAmount()));
                            account.setCurrencyId(r.getCurrencyId());
                            account.setName(r.getName());
                            account.setId(r.getId());
                            return account;
                        })
                        .collect(Collectors.toList());
            }
        } finally {
            cursor.close();
        }
        return Collections.emptyList();
    }

    @Override
    public boolean addAccount(Account account) {
        return database.persist(new AccountRecord()
                .setAmount(account.getAmount().toPlainString())
                .setCurrencyId(account.getCurrencyId())
                .setName(account.getName())
                .setId(account.getId()));
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
        Account account = new Account();
        account.setAmount(new BigDecimal(r.getAmount()));
        account.setCurrencyId(r.getCurrencyId());
        account.setName(r.getName());
        account.setId(r.getId());
        return account;
    }
}
