package com.robotnec.budget.app.util;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.robotnec.budget.app.persistence.schema.AccountRecord;
import com.robotnec.budget.core.domain.Account;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zak <zak@swingpulse.com>
 */

public final class Mapper {

    public static Account fromRecord(AccountRecord record) {
        Account account = new Account();
        account.setAmount(new BigDecimal(record.getAmount()));
        account.setCurrencyId(record.getCurrencyId());
        account.setName(record.getName());
        account.setId(record.getId());
        return account;
    }

    public static List<Account> fromRecords(List<AccountRecord> records) {
        return Stream.of(records)
                .map(Mapper::fromRecord)
                .collect(Collectors.toList());
    }

    public static AccountRecord toRecord(Account account) {
        return new AccountRecord()
                .setAmount(account.getAmount().toPlainString())
                .setCurrencyId(account.getCurrencyId())
                .setName(account.getName())
                .setId(account.getId());
    }
}
