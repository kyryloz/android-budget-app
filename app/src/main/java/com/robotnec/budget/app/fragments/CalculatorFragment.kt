package com.robotnec.budget.app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.robotnec.budget.R
import com.robotnec.budget.core.mvp.presenter.CalculatorPresenter
import com.robotnec.budget.core.mvp.view.CalculatorView
import kotlinx.android.synthetic.main.fragment_calculator.back
import kotlinx.android.synthetic.main.fragment_calculator.digit0
import kotlinx.android.synthetic.main.fragment_calculator.digit1
import kotlinx.android.synthetic.main.fragment_calculator.digit2
import kotlinx.android.synthetic.main.fragment_calculator.digit3
import kotlinx.android.synthetic.main.fragment_calculator.digit4
import kotlinx.android.synthetic.main.fragment_calculator.digit5
import kotlinx.android.synthetic.main.fragment_calculator.digit6
import kotlinx.android.synthetic.main.fragment_calculator.digit7
import kotlinx.android.synthetic.main.fragment_calculator.digit8
import kotlinx.android.synthetic.main.fragment_calculator.digit9
import kotlinx.android.synthetic.main.fragment_calculator.done
import kotlinx.android.synthetic.main.fragment_calculator.textInput
import kotlinx.android.synthetic.main.fragment_calculator.toolbar
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.support.v4.withArguments
import org.joda.money.Money

class CalculatorFragment : BasePresenterFragment<CalculatorPresenter>(), CalculatorView {

    companion object {
        const val ARG_AMOUNT = "amount"

        fun newInstance(amount: Money): Fragment {
            return CalculatorFragment().withArguments(ARG_AMOUNT to amount)
        }
    }

    private var listener: Listener? = null

    override val layoutId: Int
        get() = R.layout.fragment_calculator

    override fun createPresenter(): CalculatorPresenter {
        val amount = arguments?.get(ARG_AMOUNT) as Money
        return CalculatorPresenter(this, amount)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (context is Listener) {
            listener = context as Listener
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        fragmentManager?.beginTransaction()
                ?.remove(this)
                ?.commit()
    }

    override fun done(money: Money) {
        listener?.onMoneyEdited(money)
        fragmentManager?.beginTransaction()
                ?.remove(this)
                ?.commit()
    }

    override fun showMaxCountReached() {
        toast(R.string.max_count_reached)
    }

    interface Listener {
        fun onMoneyEdited(money: Money)
    }
}