package com.robotnec.budget.core.mvp.presenter

import com.robotnec.budget.core.di.ApplicationComponent
import com.robotnec.budget.core.domain.Account
import com.robotnec.budget.core.domain.Currency
import com.robotnec.budget.core.domain.MoneyAmount
import com.robotnec.budget.core.mvp.view.AddAccountView
import com.robotnec.budget.core.persistence.dao.AccountDao
import javax.inject.Inject

/**
 * @author zak zak@swingpulse.com>
 */
class AddAccountPresenter(view: AddAccountView, private val account: Account?) : Presenter<AddAccountView>(view) {

    @Inject
    internal lateinit var accountDao: AccountDao
    private val editMode: Boolean = this.account != null

    override fun injectComponent(applicationComponent: ApplicationComponent) {
        applicationComponent.inject(this)
    }

    override fun onViewResume() {
        if (editMode) {
            view.initEditMode(account!!)
        }
    }

    fun addOrEditAccount(accountName: String, accountAmount: String, accountCurrency: String) {
        val amount = MoneyAmount.of(java.lang.Double.parseDouble(accountAmount),
                Currency.fromCode(accountCurrency))
        if (editMode) {
            account!!.name = accountName
            account.amount = amount
            accountDao.createOrUpdate(account)
        } else {
            val account = Account(-1, accountName, amount)
            accountDao.createOrUpdate(account)
        }
    }

    fun deleteAccount() {
        accountDao.remove(account!!.id)
        view.closeView()
    }
}
