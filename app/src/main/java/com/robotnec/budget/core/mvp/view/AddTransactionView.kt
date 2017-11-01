package com.robotnec.budget.core.mvp.view

import com.robotnec.budget.core.domain.Account
import com.robotnec.budget.core.domain.Category
import org.joda.money.Money
import java.util.Calendar

/**
 * @author zak zak@swingpulse.com>
 */
interface AddTransactionView : View {
    fun initDatePickerButton(date: String)

    fun showAccountsChooserDialog(accounts: List<Account>)

    fun showCategoryChooserDialog(categories: List<Category>)

    fun showDateChooserDialog(lastDate: Calendar)

    fun showChosenDate(date: String)

    fun showCalculator(initialAmount: Money)

    fun displayAccount(account: Account)

    fun displayCategory(category: Category)

    fun displayAmount(initialAmount: Money)

    fun finish()
}
