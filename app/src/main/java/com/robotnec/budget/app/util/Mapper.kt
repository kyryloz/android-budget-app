package com.robotnec.budget.app.util

import com.robotnec.budget.core.domain.Account
import com.robotnec.budget.core.domain.Category
import com.robotnec.budget.core.domain.operation.Transaction
import com.robotnec.budget.core.persistence.schema.AccountRecord
import com.robotnec.budget.core.persistence.schema.CategoryRecord
import com.robotnec.budget.core.persistence.schema.TransactionRecord
import org.joda.money.Money

/**
 * @author zak zak@swingpulse.com>
 */
object Mapper {

    fun fromRecord(record: AccountRecord): Account {
        return Account(
                record.id,
                record.name,
                Money.parse(record.amount)
        )
    }

    fun fromAccountRecords(records: List<AccountRecord>): List<Account> {
        return records.map { fromRecord(it) }.toList()
    }

    fun toRecord(account: Account): AccountRecord {
        return AccountRecord()
                .setAmount(account.amount.toString())
                .setName(account.name)
                .also {
                    if (account.id != -1L) {
                        it.rowId = account.id
                    }
                }
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
                .setRowId(category.id)
    }

    fun fromTransactionRecords(records: List<TransactionRecord>,
                               accountMapper: (id: Long) -> Account,
                               categoryMapper: (id: Long) -> Category): List<Transaction> {
        return records.map { fromRecord(it, accountMapper, categoryMapper) }.toList()
    }

    fun fromRecord(record: TransactionRecord,
                   accountMapper: (id: Long) -> Account,
                   categoryMapper: (id: Long) -> Category): Transaction {
        return Transaction(
                record.id,
                Money.parse(record.amount),
                DateUtil.fromSeconds(record.date!!),
                accountMapper(record.accountId),
                categoryMapper(record.categoryId)
        )
    }

    fun toRecord(operation: Transaction): TransactionRecord {
        val account = operation.account
        val category = operation.category
        val amount = operation.amount
        return TransactionRecord()
                .setAccountId(account.id)
                .setCategoryId(category.id)
                .setAmount(amount.toString())
                .setDate(DateUtil.toSeconds(operation.date))
    }
}
