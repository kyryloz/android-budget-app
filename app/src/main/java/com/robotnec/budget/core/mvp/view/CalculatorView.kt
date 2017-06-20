package com.robotnec.budget.core.mvp.view

/**
 * @author zak zak@swingpulse.com>
 */
interface CalculatorView : View {
    fun display(value: String)

    fun close()

    fun done(value: Double)

    fun showMaxCountReached()
}
