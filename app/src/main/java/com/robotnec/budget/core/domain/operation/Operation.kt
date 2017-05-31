package com.robotnec.budget.core.domain.operation

/**
 * @author zak zak@swingpulse.com>
 */
interface Operation {
    fun execute(receiver: OperationReceiver): Boolean
}
