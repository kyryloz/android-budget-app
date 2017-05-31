package com.robotnec.budget.app.fragments

import android.os.Bundle
import android.transition.Slide
import android.view.View
import com.robotnec.budget.R
import com.robotnec.budget.app.dialogs.AccountPickerDialogFragment
import com.robotnec.budget.app.dialogs.CategoryPickerDialogFragment
import com.robotnec.budget.app.dialogs.DatePickerDialogFragment
import com.robotnec.budget.app.dialogs.PickerDialog
import com.robotnec.budget.core.domain.Account
import com.robotnec.budget.core.domain.Category
import com.robotnec.budget.core.domain.MoneyAmount
import com.robotnec.budget.core.mvp.presenter.AddTransactionPresenter
import com.robotnec.budget.core.mvp.view.AddTransactionView
import kotlinx.android.synthetic.main.fragment_add_transaction.*
import java.util.*

/**
 * @author zak zak@swingpulse.com>
 */
class AddTransactionFragment : BasePresenterFragment<AddTransactionPresenter>(),
        AddTransactionView,
        DatePickerDialogFragment.Listener,
        AccountPickerDialogFragment.Listener,
        CategoryPickerDialogFragment.Listener {

    override val layoutId: Int
        get() = R.layout.fragment_add_transaction

    override fun createPresenter(): AddTransactionPresenter {
        return AddTransactionPresenter(this)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutAmount.setOnClickListener { presenter.openCalculator() }
        layoutDate.setOnClickListener { presenter.changeDate() }
        layoutAccount.setOnClickListener { presenter.changeAccount() }
        layoutExpenseCategory.setOnClickListener { presenter.changeCategory() }
        buttonDone.setOnClickListener {
            presenter.submit()
            activity.finish()
        }
    }

    override fun onDatePicked(calendar: Calendar) {
        presenter.setChosenDate(calendar)
    }

    override fun onPicked(account: Account) {
        presenter.pickAccount(account)
    }

    override fun onPicked(category: Category) {
        presenter.pickCategory(category)
    }

    override fun initDatePickerButton(date: String) {
        textDate.text = date
    }

    override fun showAccountsChooserDialog(accounts: List<Account>) {
        val dialog = AccountPickerDialogFragment.newInstance(
                accounts,
                this)
        dialog.show(fragmentManager, PickerDialog.TAG)
    }

    override fun showCategoryChooserDialog(categories: List<Category>) {
        val dialog = CategoryPickerDialogFragment.newInstance(
                categories,
                this)
        dialog.show(fragmentManager, PickerDialog.TAG)
    }

    override fun showChosenDate(date: String) {
        textDate.text = date
    }

    override fun showDateChooserDialog(lastDate: Calendar) {
        val datePicker = DatePickerDialogFragment.newInstance(lastDate, this)
        datePicker.show(fragmentManager, DatePickerDialogFragment.TAG)
    }

    override fun showCalculator(initialAmount: MoneyAmount) {
        val fragment = CalculatorFragment.newInstance(initialAmount)
        fragment.enterTransition = Slide()
        fragment.exitTransition = Slide()
        fragmentManager.beginTransaction()
                .replace(R.id.frame_calculator, fragment)
                .addToBackStack(null)
                .commit()
    }

    override fun displayAccount(account: Account) {
        textAccountName.text = account.name
        textAccountAmount.text = account.amount.toDisplayableString()
    }

    override fun displayCategory(category: Category) {
        textExpenseCategory.text = category.name
    }

    override fun displayInitialAmount(initialAmount: MoneyAmount) {
        textAmount.text = initialAmount.toDisplayableString()
    }

    override fun finish() {
        activity.finish()
    }
}
