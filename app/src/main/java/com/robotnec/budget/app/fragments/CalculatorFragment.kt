package com.robotnec.budget.app.fragments

import android.os.Bundle
import android.view.View
import com.robotnec.budget.R
import com.robotnec.budget.app.util.Keys
import com.robotnec.budget.core.domain.MoneyAmount
import com.robotnec.budget.core.mvp.presenter.CalculatorPresenter
import com.robotnec.budget.core.mvp.view.CalculatorView
import kotlinx.android.synthetic.main.fragment_calculator.*
import org.jetbrains.anko.support.v4.toast

class CalculatorFragment : BasePresenterFragment<CalculatorPresenter>(), CalculatorView {

    companion object {

        fun newInstance(initialAmount: MoneyAmount): CalculatorFragment {
            val args = Bundle()
            args.putSerializable(Keys.AMOUNT, initialAmount)
            val fragment = CalculatorFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override val layoutId: Int
        get() = R.layout.fragment_calculator

    override fun createPresenter(): CalculatorPresenter {
        return CalculatorPresenter(this)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbarBack(toolbar)
        toolbar.title = null
        toolbar.setNavigationOnClickListener { close() }

        initButtons()
    }

    private fun initButtons() {
        listOf(digit0, digit1, digit2, digit3, digit4,
                digit5, digit6, digit7, digit8, digit9)
                .forEach {
                    it.setOnClickListener { view ->
                        when (view.id) {
                            R.id.digit0 -> presenter.digit(0)
                            R.id.digit1 -> presenter.digit(1)
                            R.id.digit2 -> presenter.digit(2)
                            R.id.digit3 -> presenter.digit(3)
                            R.id.digit4 -> presenter.digit(4)
                            R.id.digit5 -> presenter.digit(5)
                            R.id.digit6 -> presenter.digit(6)
                            R.id.digit7 -> presenter.digit(7)
                            R.id.digit8 -> presenter.digit(8)
                            R.id.digit9 -> presenter.digit(9)
                            else -> throw IllegalArgumentException()
                        }
                    }
                }

        back.setOnClickListener { presenter.back() }
        done.setOnClickListener { presenter.done() }
    }

    override fun display(value: String) {
        textInput.text = value
    }

    override fun close() {
        fragmentManager.beginTransaction()
                .remove(this)
                .commit()
    }

    override fun done(value: Double) {
        fragmentManager.beginTransaction()
                .remove(this)
                .commit()
    }

    override fun showMaxCountReached() {
        toast(R.string.max_count_reached)
    }
}