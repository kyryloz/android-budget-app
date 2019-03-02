package com.robotnec.budget.core.mvp.presenter

import com.robotnec.budget.app.util.DateUtil
import com.robotnec.budget.refactored.di.ApplicationComponent
import com.robotnec.budget.core.domain.Account
import com.robotnec.budget.core.domain.Category
import com.robotnec.budget.core.domain.operation.Expense
import com.robotnec.budget.core.mvp.view.AddTransactionView
import com.robotnec.budget.core.persistence.dao.AccountDao
import com.robotnec.budget.core.persistence.dao.CategoryDao
import com.robotnec.budget.core.service.MoneyOperationBroker
import org.joda.money.CurrencyUnit
import org.joda.money.Money
import java.text.DateFormat
import java.util.Calendar
import javax.inject.Inject

/**
 * @author zak zak@swingpulse.com>
 */
class AddTransactionPresenter(view: AddTransactionView) : Presenter<AddTransactionView>(view) {

    @Inject
    internal lateinit var accountDao: AccountDao

    @Inject
    internal lateinit var categoryDao: CategoryDao

    @Inject
    internal lateinit var moneyOperationBroker: MoneyOperationBroker

    private var resultDate: Calendar = Calendar.getInstance()
    private lateinit var dateFormat: DateFormat
    private lateinit var targetAccount: Account
    private lateinit var targetCategory: Category
    private lateinit var resultMoney: Money

    override fun onViewResume() {
        dateFormat = DateFormat.getDateInstance()
        val format = dateFormat.format(Calendar.getInstance().time)
        view.initDatePickerButton(format)

        val allAccounts = accountDao.all
        targetAccount = allAccounts[0]
        view.displayAccount(targetAccount)

        val allCategories = categoryDao.all
        targetCategory = allCategories[0]
        view.displayCategory(targetCategory)

        resultMoney = Money.of(CurrencyUnit.USD, 0.0)
        view.displayAmount(resultMoney)
    }

    override fun injectComponent(applicationComponent: ApplicationComponent) {
        applicationComponent.inject(this)
    }

    fun changeAccount() {
        view.showAccountsChooserDialog(accountDao.all)
    }

    fun changeCategory() {
        view.showCategoryChooserDialog(categoryDao.all)
    }

    fun setChosenDate(calendar: Calendar) {
        resultDate = calendar
        view.showChosenDate(dateFormat.format(calendar.time))
    }

    fun changeDate() {
        view.showDateChooserDialog(resultDate)
    }

    fun submit() {
        val moneyAmount = Money.of(resultMoney)
        val expense = Expense(
                moneyAmount,
                DateUtil.fromSeconds(resultDate.timeInMillis / 1000),
                targetAccount,
                targetCategory)
        moneyOperationBroker.execute(expense)
        view.finish()
    }

    fun pickAccount(account: Account) {
        targetAccount = account
        view.displayAccount(targetAccount)
    }

    fun pickCategory(category: Category) {
        targetCategory = category
        view.displayCategory(category)
    }

    fun openCalculator() {
        view.showCalculator(resultMoney)
    }

    fun setMoney(money: Money) {
        resultMoney = money
        view.displayAmount(resultMoney)
    }
}
