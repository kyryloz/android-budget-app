package com.robotnec.budget.app.util;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;
import com.robotnec.budget.core.domain.Account;
import com.robotnec.budget.core.domain.Category;
import com.robotnec.budget.core.domain.MoneyAmount;
import com.robotnec.budget.core.domain.operation.Transaction;
import com.robotnec.budget.core.persistence.schema.AccountRecord;
import com.robotnec.budget.core.persistence.schema.CategoryRecord;
import com.robotnec.budget.core.persistence.schema.TransactionRecord;

import java.util.List;

/**
 * @author zak <zak@swingpulse.com>
 */

public final class Mapper {

    public static Account fromRecord(AccountRecord record) {
        return new Account(
                record.getId(),
                record.getName(),
                MoneyAmount.fromDbString(record.getAmount())
        );
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
        return new Category(record.getId(), record.getName());
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

    public static List<Transaction> fromTransactionRecords(List<TransactionRecord> records,
                                                           Function<Long, Account> accountMapper,
                                                           Function<Long, Category> categoryMapper) {
        return Stream.of(records)
                .map(record -> fromRecord(record, accountMapper, categoryMapper))
                .collect(Collectors.toList());
    }

    public static Transaction fromRecord(TransactionRecord record,
                                         Function<Long, Account> accountMapper,
                                         Function<Long, Category> categoryMapper) {
        return new Transaction(
                record.getId(),
                MoneyAmount.fromDbString(record.getAmount()),
                DateUtil.fromSeconds(record.getDate()),
                accountMapper.apply(record.getAccountId()),
                categoryMapper.apply(record.getCategoryId())
        );
    }

    public static TransactionRecord toRecord(Transaction operation) {
        Account account = operation.getAccount();
        Category category = operation.getCategory();
        MoneyAmount amount = operation.getAmount();
        return new TransactionRecord()
                .setId(operation.getId())
                .setAccountId(account.getId())
                .setCategoryId(category.getId())
                .setAmount(amount.toDbString())
                .setDate(DateUtil.toSeconds(operation.getDate()));
    }
}
