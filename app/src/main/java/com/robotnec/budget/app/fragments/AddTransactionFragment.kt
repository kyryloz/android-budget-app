package com.robotnec.budget.app.fragments

import android.os.Bundle
import android.transition.Slide
import android.view.View
import com.robotnec.budget.R
import com.robotnec.budget.app.dialogs.DatePickerDialogFragment
import com.robotnec.budget.core.domain.Account
import com.robotnec.budget.core.domain.Category
import com.robotnec.budget.core.mvp.presenter.AddTransactionPresenter
import com.robotnec.budget.core.mvp.view.AddTransactionView
import kotlinx.android.synthetic.main.fragment_add_transaction.buttonDone
import kotlinx.android.synthetic.main.fragment_add_transaction.layoutAccount
import kotlinx.android.synthetic.main.fragment_add_transaction.layoutAmount
import kotlinx.android.synthetic.main.fragment_add_transaction.layoutDate
import kotlinx.android.synthetic.main.fragment_add_transaction.layoutExpenseCategory
import kotlinx.android.synthetic.main.fragment_add_transaction.textAccountAmount
import kotlinx.android.synthetic.main.fragment_add_transaction.textAccountName
import kotlinx.android.synthetic.main.fragment_add_transaction.textAmount
import kotlinx.android.synthetic.main.fragment_add_transaction.textDate
import kotlinx.android.synthetic.main.fragment_add_transaction.textExpenseCategory
import org.jetbrains.anko.support.v4.selector
import org.joda.money.Money
import java.util.Calendar

/**
 * @author zak zak@swingpulse.com>
 */
class AddTransactionFragment : BasePresenterFragment<AddTransactionPresenter>(),
        AddTransactionView,
        DatePickerDialogFragment.Listener {

    override val layoutId: Int
        get() = R.layout.fragment_add_transaction

    override fun createPresenter(): AddTransactionPresenter {
        return AddTransactionPresenter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutAmount.setOnClickListener { presenter.openCalculator() }
        layoutDate.setOnClickListener { presenter.changeDate() }
        layoutAccount.setOnClickListener { presenter.changeAccount() }
        layoutExpenseCategory.setOnClickListener { presenter.changeCategory() }
        buttonDone.setOnClickListener {
            presenter.submit()
            activity?.finish()
        }
    }

    override fun onDatePicked(calendar: Calendar) {
        presenter.setChosenDate(calendar)
    }

    override fun initDatePickerButton(date: String) {
        textDate.text = date
    }

    override fun showAccountsChooserDialog(accounts: List<Account>) {
        selector(getString(R.string.make_transaction_select_account),
                accounts.map { it.name }) { _, i ->
            presenter.pickAccount(accounts[i])
        }
    }

    override fun showCategoryChooserDialog(categories: List<Category>) {
        selector(getString(R.string.make_transaction_select_category),
                categories.map { it.name }) { _, i ->
            presenter.pickCategory(categories[i])
        }
    }

    override fun showDateChooserDialog(lastDate: Calendar) {
        val datePicker = DatePickerDialogFragment.newInstance(lastDate, this)
        datePicker.show(requireFragmentManager(), DatePickerDialogFragment.TAG)
    }

    override fun showChosenDate(date: String) {
        textDate.text = date
    }

    override fun showCalculator(initialAmount: Money) {
        val fragment = CalculatorFragment.newInstance(initialAmount)
        fragment.enterTransition = Slide()
        fragment.exitTransition = Slide()
        fragmentManager?.beginTransaction()
                ?.replace(R.id.frame_calculator, fragment)
                ?.addToBackStack(null)
                ?.commit()
    }

    override fun displayAccount(account: Account) {
        textAccountName.text = account.name
        textAccountAmount.text = account.amount.toString()
    }

    override fun displayCategory(category: Category) {
        textExpenseCategory.text = category.name
    }

    override fun displayAmount(initialAmount: Money) {
        textAmount.text = initialAmount.toString()
    }

    override fun finish() {
        activity?.finish()
    }

    fun onMoneyEdited(money: Money) {
        presenter.setMoney(money)
    }
}
