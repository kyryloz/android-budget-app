package com.robotnec.budget.core.mvp.view

/**
 * @author zak zak@swingpulse.com>
 */
interface CalculatorView : View {
    fun display(value: String)

    fun displayError()

    fun displayDone()

    fun close()

    fun clearError()

    fun clearDone()

    fun done(value: Double)
}
