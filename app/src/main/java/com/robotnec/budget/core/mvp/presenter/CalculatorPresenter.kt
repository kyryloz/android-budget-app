package com.robotnec.budget.core.mvp.presenter

import android.util.Log
import com.robotnec.budget.core.di.ApplicationComponent
import com.robotnec.budget.core.domain.MoneyAmount
import com.robotnec.budget.core.mvp.view.CalculatorView
import org.jetbrains.anko.AnkoLogger

/**
 * @author zak zak@swingpulse.com>
 */
class CalculatorPresenter(view: CalculatorView, amount: MoneyAmount?)
    : Presenter<CalculatorView>(view), AnkoLogger {

    companion object {
        const val MAX_NUMBERS_COUNT: Int = 16
    }

    private val currentValue: MutableList<Int> = mutableListOf()

    init {
        if (amount != null) {
            // TODO
        }
    }

    override fun injectComponent(applicationComponent: ApplicationComponent) {
        applicationComponent.inject(this)
    }

    override fun onViewResume() {
        view.display(format(calculateValue()))
    }

    fun digit(digit: Int) {
        if (currentValue.size < MAX_NUMBERS_COUNT) {
            currentValue.add(digit)
            view.display(format(calculateValue()))
        } else {
            view.showMaxCountReached()
        }
    }

    fun done() {
        view.done(calculateValue())
    }

    fun back() {
        if (currentValue.size > 0) {
            currentValue.removeAt(currentValue.size - 1)
            view.display(format(calculateValue()))
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

    private fun format(value: Double): String {
        return "%.2f".format(value)
    }
}
