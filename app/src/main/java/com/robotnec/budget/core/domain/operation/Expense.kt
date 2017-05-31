package com.robotnec.budget.core.domain.operation

import com.robotnec.budget.core.domain.Account
import com.robotnec.budget.core.domain.Category
import com.robotnec.budget.core.domain.MoneyAmount

import org.threeten.bp.LocalDateTime


/**
 * @author zak zak@swingpulse.com>
 */
class Expense(val amount: MoneyAmount,
              val date: LocalDateTime,
              val account: Account,
              val category: Category) : Operation {

    override fun execute(receiver: OperationReceiver): Boolean {
        return receiver.receive(this)
    }
}
