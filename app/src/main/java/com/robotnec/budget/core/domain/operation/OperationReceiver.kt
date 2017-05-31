package com.robotnec.budget.core.domain.operation

/**
 * @author zak zak@swingpulse.com>
 */
interface OperationReceiver {
    fun receive(expense: Expense): Boolean

    fun receive(income: Income): Boolean
}
