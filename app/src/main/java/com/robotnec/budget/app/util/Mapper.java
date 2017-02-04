package com.robotnec.budget.app.util;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.robotnec.budget.app.persistence.schema.AccountRecord;
import com.robotnec.budget.app.persistence.schema.CategoryRecord;
import com.robotnec.budget.core.domain.Account;
import com.robotnec.budget.core.domain.Category;

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

    public static List<Account> fromAccountRecords(List<AccountRecord> records) {
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

    public static Category fromRecord(CategoryRecord record) {
        Category category = new Category();
        category.setId(record.getId());
        category.setName(record.getName());
        return category;
    }

    public static List<Category> fromCategoryRecords(List<CategoryRecord> records) {
        return Stream.of(records)
                .map(Mapper::fromRecord)
                .collect(Collectors.toList());
    }

    public static CategoryRecord toRecord(Category category) {
        return new CategoryRecord()
                .setName(category.getName())
                .setId(category.getId());
    }
}
