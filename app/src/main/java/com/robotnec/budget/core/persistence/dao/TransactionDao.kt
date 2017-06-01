package com.robotnec.budget.core.persistence.dao

import com.robotnec.budget.core.domain.operation.Transaction

/**
 * @author zak zak@swingpulse.com>
 */
interface TransactionDao : BaseDao<Transaction> {
    fun getTransactionsForCategory(categoryId: Long): List<Transaction>
}
