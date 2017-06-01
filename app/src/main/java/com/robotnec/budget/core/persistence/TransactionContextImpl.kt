package com.robotnec.budget.core.persistence

import com.yahoo.squidb.data.SquidDatabase

/**
 * @author zak zak@swingpulse.com>
 */
class TransactionContextImpl(private val database: SquidDatabase) : TransactionContext {

    override fun doInTransaction(transaction: () -> Boolean): Boolean {
        val success: Boolean
        database.beginTransaction()
        try {
            success = transaction()
            if (success) {
                database.setTransactionSuccessful()
            }
        } finally {
            database.endTransaction()
        }
        return success
    }
}
