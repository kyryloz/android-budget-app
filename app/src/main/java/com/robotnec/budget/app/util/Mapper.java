package com.robotnec.budget.app.util;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;
import com.robotnec.budget.app.persistence.schema.AccountRecord;
import com.robotnec.budget.app.persistence.schema.CategoryRecord;
import com.robotnec.budget.app.persistence.schema.TransactionRecord;
import com.robotnec.budget.core.domain.Account;
import com.robotnec.budget.core.domain.Category;
import com.robotnec.budget.core.domain.MoneyAmount;
import com.robotnec.budget.core.domain.Transaction;

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

    public static List<Transaction> fromTransactionRecords(List<TransactionRecord> records,
                                                           Function<Long, Account> accountMapper,
                                                           Function<Long, Category> categoryMapper) {
        return Stream.of(records)
                .map((record) -> fromRecord(record, accountMapper, categoryMapper))
                .collect(Collectors.toList());
    }

    public static Transaction fromRecord(TransactionRecord record,
                                         Function<Long, Account> accountMapper,
                                         Function<Long, Category> categoryMapper) {
        Transaction transaction = new Transaction();
        transaction.setAmount(MoneyAmount.fromDbString(record.getAmount()));
        transaction.setAccount(accountMapper.apply(record.getAccountId()));
        transaction.setCategory(categoryMapper.apply(record.getCategoryId()));
        transaction.setId(record.getId());
        transaction.setDate(record.getDate());
        return transaction;
    }

    public static TransactionRecord toRecord(Transaction transaction) {
        return new TransactionRecord()
                .setId(transaction.getId())
                .setAccountId(transaction.getAccount().getId())
                .setCategoryId(transaction.getCategory().getId())
                .setAmount(transaction.getAmount().toDbString())
                .setDate(transaction.getDate());
    }
}
