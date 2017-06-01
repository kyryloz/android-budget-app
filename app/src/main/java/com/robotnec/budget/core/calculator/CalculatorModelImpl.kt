package com.robotnec.budget.core.calculator

import com.robotnec.budget.core.calculator.eval.Evaluator
import com.robotnec.budget.core.calculator.eval.JEvalEvaluator
import com.robotnec.budget.core.exception.InvalidExpressionException
import java.text.DecimalFormat

/**
 * @author zak zak@swingpulse.com>
 */
class CalculatorModelImpl : CalculatorModel {

    private val INIT_STATE = 0
    private val DIGIT_STATE = 1
    private val OPERATION_STATE = 2
    private val ERROR_STATE = 3

    private val displayFormat: DecimalFormat

    private var state: Int = 0
    private val input: Input
    private val evaluator: Evaluator

    init {
        state = INIT_STATE
        input = Input()
        evaluator = JEvalEvaluator()
        displayFormat = DecimalFormat("#.##")
    }

    override fun digit(digit: Int): String {
        val digitStr = digit.toString()
        when (state) {
            ERROR_STATE, INIT_STATE -> {
                state = DIGIT_STATE
                input.replace(digitStr)
            }
            DIGIT_STATE -> input.append(digitStr)
            OPERATION_STATE -> {
                state = DIGIT_STATE
                input.append(digitStr)
            }
            else -> throw IllegalArgumentException()
        }
        return input.toDisplayText()
    }

    override fun dot(): String {
        val dot = "."
        when (state) {
            ERROR_STATE, INIT_STATE -> {
                input.replace(dot)
                state = DIGIT_STATE
            }
            DIGIT_STATE -> {
                val dotEntry = Input.Entry(dot, dot, false)
                val fakeOperationEntry = Input.Entry("", "", true)
                val entries = input.inputStack
                        .map { if (it.isOperation) fakeOperationEntry else dotEntry }
                        .toList()
                val indexDotEntry = entries.lastIndexOf(dotEntry)
                val indexOperationEntry = entries.lastIndexOf(fakeOperationEntry)
                if (indexDotEntry <= indexOperationEntry) {
                    input.append(dot)
                }
            }
            OPERATION_STATE -> {
                input.append(dot)
                state = DIGIT_STATE
            }
            else -> throw IllegalArgumentException()
        }
        return input.toDisplayText()
    }

    @Throws(InvalidExpressionException::class)
    override fun calculate(): String {
        when (state) {
            OPERATION_STATE, ERROR_STATE -> throw InvalidExpressionException()
            INIT_STATE -> {
            }
            DIGIT_STATE -> {
                var resultStr: String
                try {
                    resultStr = evaluator.eval(input.toExpression())
                } catch (e: InvalidExpressionException) {
                    state = ERROR_STATE
                    throw e
                }

                val resultNumber = java.lang.Double.parseDouble(resultStr)
                if (java.lang.Double.isInfinite(resultNumber) || java.lang.Double.isNaN(resultNumber)) {
                    state = ERROR_STATE
                    return resultNumber.toString()
                } else {
                    resultStr = displayFormat.format(resultNumber)
                    input.clear()
                    for (c in resultStr.toCharArray()) {
                        input.append(c.toString())
                    }
                    state = DIGIT_STATE
                }
            }
            else -> throw IllegalArgumentException()
        }
        return input.toDisplayText()
    }

    override fun operation(op: Op): String {
        when (state) {
            ERROR_STATE -> {
                input.clear()
                state = INIT_STATE
            }
            INIT_STATE -> {
            }
            DIGIT_STATE -> {
                state = OPERATION_STATE
                input.append(op)
            }
            OPERATION_STATE -> {
            }
            else -> throw IllegalArgumentException()
        }
        return input.toDisplayText()
    }

    override fun back(): String {
        when (state) {
            ERROR_STATE -> {
                input.clear()
                state = INIT_STATE
            }
            INIT_STATE -> {
            }
            DIGIT_STATE, OPERATION_STATE -> {
                input.delete()
                val inputStack = input.inputStack
                if (inputStack.isEmpty()) {
                    state = INIT_STATE
                } else if (inputStack.peekLast().isOperation) {
                    state = OPERATION_STATE
                } else {
                    state = DIGIT_STATE
                }
            }
            else -> throw IllegalArgumentException()
        }
        return input.toDisplayText()
    }

    override fun clear(): String {
        input.clear()
        state = INIT_STATE
        return input.toDisplayText()
    }
}
