package com.robotnec.budget.app.util

import com.annimon.stream.function.Function
import com.robotnec.budget.core.domain.Account
import com.robotnec.budget.core.domain.Category
import com.robotnec.budget.core.domain.MoneyAmount
import com.robotnec.budget.core.domain.operation.Transaction
import com.robotnec.budget.core.persistence.schema.AccountRecord
import com.robotnec.budget.core.persistence.schema.CategoryRecord
import com.robotnec.budget.core.persistence.schema.TransactionRecord

/**
 * @author zak zak@swingpulse.com>
 */
object Mapper {

    fun fromRecord(record: AccountRecord): Account {
        return Account(
                record.id,
                record.name,
                MoneyAmount.fromDbString(record.amount)
        )
    }

    fun fromAccountRecords(records: List<AccountRecord>): List<Account> {
        return records.map { fromRecord(it) }.toList()
    }

    fun toRecord(account: Account): AccountRecord {
        return AccountRecord()
                .setAmount(account.amount.toDbString())
                .setName(account.name)
                .setId(account.id)
    }

    fun fromRecord(record: CategoryRecord): Category {
        return Category(record.id, record.name)
    }

    fun fromCategoryRecords(records: List<CategoryRecord>): List<Category> {
        return records.map { fromRecord(it) }.toList()
    }

    fun toRecord(category: Category): CategoryRecord {
        return CategoryRecord()
                .setName(category.name)
                .setId(category.id)
    }

    fun fromTransactionRecords(records: List<TransactionRecord>,
                               accountMapper: Function<Long, Account>,
                               categoryMapper: Function<Long, Category>): List<Transaction> {
        return records.map { fromRecord(it, accountMapper, categoryMapper) }.toList()
    }

    fun fromRecord(record: TransactionRecord,
                   accountMapper: Function<Long, Account>,
                   categoryMapper: Function<Long, Category>): Transaction {
        return Transaction(
                record.id,
                MoneyAmount.fromDbString(record.amount),
                DateUtil.fromSeconds(record.date!!),
                accountMapper.apply(record.accountId),
                categoryMapper.apply(record.categoryId)
        )
    }

    fun toRecord(operation: Transaction): TransactionRecord {
        val account = operation.account
        val category = operation.category
        val amount = operation.amount
        return TransactionRecord()
                .setId(operation.id)
                .setAccountId(account.id)
                .setCategoryId(category.id)
                .setAmount(amount.toDbString())
                .setDate(DateUtil.toSeconds(operation.date))
    }
}
