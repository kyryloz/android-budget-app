package com.robotnec.budget.core.calculator

import com.robotnec.budget.core.exception.InvalidExpressionException

/**
 * @author zak zak@swingpulse.com>
 */
interface CalculatorModel {
    fun digit(digit: Int): String

    fun dot(): String

    @Throws(InvalidExpressionException::class)
    fun calculate(): String

    fun operation(op: Op): String

    fun back(): String

    fun clear(): String
}
