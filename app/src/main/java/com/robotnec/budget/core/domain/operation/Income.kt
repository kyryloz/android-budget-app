package com.robotnec.budget.core.domain.operation

import com.robotnec.budget.core.domain.Account

/**
 * @author zak zak@swingpulse.com>
 */
// example
class Income(val date: Long,
             val account: Account) : Operation {

    override fun execute(receiver: OperationReceiver): Boolean {
        return receiver.receive(this)
    }
}
