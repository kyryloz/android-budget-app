package com.robotnec.budget.core.calculator.eval

import com.robotnec.budget.core.exception.InvalidExpressionException

import net.sourceforge.jeval.EvaluationException

/**
 * @author zak zak@swingpulse.com>
 */
class JEvalEvaluator : Evaluator {

    private val evaluator = net.sourceforge.jeval.Evaluator()

    @Throws(InvalidExpressionException::class)
    override fun eval(expression: String): String {
        try {
            return evaluator.evaluate(expression)
        } catch (e: EvaluationException) {
            throw InvalidExpressionException(e)
        }

    }
}
