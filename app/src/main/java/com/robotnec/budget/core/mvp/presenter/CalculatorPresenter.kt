package com.robotnec.budget.core.mvp.presenter

import com.robotnec.budget.core.calculator.CalculatorModel
import com.robotnec.budget.core.calculator.CalculatorModelImpl
import com.robotnec.budget.core.calculator.Op
import com.robotnec.budget.core.di.ApplicationComponent
import com.robotnec.budget.core.exception.InvalidExpressionException
import com.robotnec.budget.core.mvp.view.CalculatorView

/**
 * @author zak zak@swingpulse.com>
 */
class CalculatorPresenter(view: CalculatorView) : Presenter<CalculatorView>(view) {

    private val calculatorModel: CalculatorModel
    private var done: Boolean = false

    init {
        calculatorModel = CalculatorModelImpl()
        done = false
    }

    override fun injectComponent(applicationComponent: ApplicationComponent) {
        applicationComponent.inject(this)
    }

    override fun onViewResume() {
        calculate()
    }

    fun digit(digit: Int) {
        display(calculatorModel.digit(digit))
    }

    fun dot() {
        display(calculatorModel.dot())
    }

    fun calculate() {
        try {
            val displayText = calculatorModel.calculate()
            val number = java.lang.Double.parseDouble(displayText)
            if (java.lang.Double.isInfinite(number) || java.lang.Double.isNaN(number)) {
                display(displayText)
                view.displayError()
            } else {
                if (done) {
                    view.done(java.lang.Double.parseDouble(displayText))
                } else {
                    display(displayText)
                    done = true
                    view.displayDone()
                }
            }
        } catch (e: InvalidExpressionException) {
            view.displayError()
        }

    }

    fun divide() {
        display(calculatorModel.operation(Op.DIVIDE))
    }

    fun multiply() {
        display(calculatorModel.operation(Op.MULTIPLY))
    }

    fun minus() {
        display(calculatorModel.operation(Op.MINUS))
    }

    fun plus() {
        display(calculatorModel.operation(Op.PLUS))
    }

    fun back() {
        display(calculatorModel.back())
    }

    fun clear() {
        display(calculatorModel.clear())
    }

    private fun display(displayText: String) {
        view.display(displayText)
        view.clearError()
        view.clearDone()
        done = false
    }
}
