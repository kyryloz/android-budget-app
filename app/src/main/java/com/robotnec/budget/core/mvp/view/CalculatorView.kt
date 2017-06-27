package com.robotnec.budget.core.mvp.view

import org.joda.money.Money

/**
 * @author zak zak@swingpulse.com>
 */
interface CalculatorView : View {
    fun display(value: String)

    fun close()

    fun done(money: Money)

    fun showMaxCountReached()
}
