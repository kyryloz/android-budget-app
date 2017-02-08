package com.robotnec.budget.app.util;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;
import com.robotnec.budget.core.persistence.schema.AccountRecord;
import com.robotnec.budget.core.persistence.schema.CategoryRecord;
import com.robotnec.budget.core.persistence.schema.MoneyOperationRecord;
import com.robotnec.budget.core.domain.Account;
import com.robotnec.budget.core.domain.Category;
import com.robotnec.budget.core.domain.MoneyAmount;
import com.robotnec.budget.core.domain.operation.MoneyOperation;

import java.util.List;

/**
 * @author zak <zak@swingpulse.com>
 */

public final class Mapper {

    public static Account fromRecord(AccountRecord record) {
        Account account = new Account();
        MoneyAmount amount = MoneyAmount.fromDbString(record.getAmount());
        account.setAmount(amount);
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
                .setAmount(account.getAmount().toDbString())
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

    public static List<MoneyOperation> fromTransactionRecords(List<MoneyOperationRecord> records,
                                                             Function<Long, Account> accountMapper,
                                                             Function<Long, Category> categoryMapper) {
        return Stream.of(records)
                .map(record -> fromRecord(record, accountMapper, categoryMapper))
                .collect(Collectors.toList());
    }

    public static MoneyOperation fromRecord(MoneyOperationRecord record,
                                            Function<Long, Account> accountMapper,
                                            Function<Long, Category> categoryMapper) {
        MoneyOperation moneyOperation = new MoneyOperation();
        moneyOperation.setAmount(MoneyAmount.fromDbString(record.getAmount()));
        moneyOperation.setAccount(accountMapper.apply(record.getAccountId()));
        moneyOperation.setCategory(categoryMapper.apply(record.getCategoryId()));
        moneyOperation.setId(record.getId());
        moneyOperation.setDate(record.getDate());
        return moneyOperation;
    }

    public static MoneyOperationRecord toRecord(MoneyOperation operation) {
        Account account = operation.getAccount();
        Category category = operation.getCategory();
        MoneyAmount amount = operation.getAmount();
        return new MoneyOperationRecord()
                .setId(operation.getId())
                .setAccountId(account != null ? account.getId() : 1)
                .setCategoryId(category != null ? category.getId() : 1)
                .setAmount(amount != null ? amount.toDbString() : "-1 UAH")
                .setDate(operation.getDate());
    }
}
