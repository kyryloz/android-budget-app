package com.robotnec.budget.core.mvp.presenter

import com.robotnec.budget.core.di.ApplicationComponent
import com.robotnec.budget.core.mvp.view.CalculatorView
import org.jetbrains.anko.AnkoLogger

/**
 * @author zak zak@swingpulse.com>
 */
class CalculatorPresenter(view: CalculatorView) : Presenter<CalculatorView>(view), AnkoLogger {

    companion object {
        const val MAX_NUMBERS_COUNT: Int = 16
    }

    private val currentValue: MutableList<Int> = mutableListOf()

    override fun injectComponent(applicationComponent: ApplicationComponent) {
        applicationComponent.inject(this)
    }

    override fun onViewResume() {
    }

    fun digit(digit: Int) {
        if (currentValue.size < MAX_NUMBERS_COUNT) {
            currentValue.add(digit)
            view.display("%.2f".format(calculateValue()))
        }
    }

    fun done() {
        val value = currentValue
                .joinToString(separator = "")
                .toDouble() / 100
        view.done(value)
    }

    fun back() {
        if (currentValue.size > 0) {
            currentValue.removeAt(currentValue.size - 1)
            view.display("%.2f".format(calculateValue()))
        }
    }

    private fun calculateValue(): Double {
        var value = 0.0
        if (currentValue.size > 0) {
            value = currentValue
                    .joinToString(separator = "")
                    .toDouble() / 100
        }
        return value
    }
}
