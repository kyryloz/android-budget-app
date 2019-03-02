package com.robotnec.budget.core.mvp.presenter

import com.robotnec.budget.refactored.di.ApplicationComponent
import com.robotnec.budget.core.mvp.view.CalculatorView
import org.joda.money.CurrencyUnit
import org.joda.money.Money

/**
 * @author zak zak@swingpulse.com>
 */
class CalculatorPresenter(view: CalculatorView, var amount: Money?)
    : Presenter<CalculatorView>(view) {

    companion object {
        const val MAX_NUMBERS_COUNT: Int = 16
    }

    private val input: MutableList<Int> = mutableListOf()

    override fun injectComponent(applicationComponent: ApplicationComponent) {
        applicationComponent.inject(this)
    }

    override fun onViewResume() {
        view.display(amount.toString())
    }

    fun digit(digit: Int) {
        if (input.size < MAX_NUMBERS_COUNT) {
            input.add(digit)
            view.display(createMoney(input)
                    .also { amount = it }
                    .toString())
        } else {
            view.showMaxCountReached()
        }
    }

    fun done() {
        view.done(createMoney(input))
    }

    fun back() {
        if (input.size > 0) {
            input.removeAt(input.size - 1)
            view.display(createMoney(input)
                    .also { amount = it }
                    .toString())
        } else {
            amount = Money.of(CurrencyUnit.USD, 0.0)
            view.display(amount.toString())
        }
    }

    private fun createMoney(input: MutableList<Int>): Money {
        var value = 0.0
        if (input.size > 0) {
            value = input
                    .joinToString(separator = "")
                    .toDouble() / 100
        }
        return Money.of(CurrencyUnit.USD, value)
    }
}
