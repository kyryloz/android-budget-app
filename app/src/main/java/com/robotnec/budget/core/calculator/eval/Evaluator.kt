package com.robotnec.budget.core.calculator.eval

import com.robotnec.budget.core.exception.InvalidExpressionException

/**
 * @author zak zak@swingpulse.com>
 */
interface Evaluator {
    @Throws(InvalidExpressionException::class)
    fun eval(expression: String): String
}
