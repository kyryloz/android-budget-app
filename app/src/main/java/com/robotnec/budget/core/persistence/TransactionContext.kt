package com.robotnec.budget.core.persistence

/**
 * @author zak zak@swingpulse.com>
 */
interface TransactionContext {

    fun doInTransaction(transaction: () -> Boolean): Boolean
}
