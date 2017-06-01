package com.robotnec.budget.core.mvp.view

import java.util.Calendar

import com.robotnec.budget.core.domain.Account
import com.robotnec.budget.core.domain.Category
import com.robotnec.budget.core.domain.MoneyAmount

/**
 * @author zak zak@swingpulse.com>
 */
interface AddTransactionView : View {
    fun initDatePickerButton(date: String)

    fun showAccountsChooserDialog(accounts: List<Account>)

    fun showCategoryChooserDialog(categories: List<Category>)

    fun showChosenDate(date: String)

    fun showDateChooserDialog(lastDate: Calendar)

    fun showCalculator(initialAmount: MoneyAmount)

    fun displayAccount(account: Account)

    fun displayCategory(category: Category)

    fun displayInitialAmount(initialAmount: MoneyAmount)

    fun finish()
}
