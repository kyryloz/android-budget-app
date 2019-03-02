package com.robotnec.budget.core.mvp.presenter

import com.robotnec.budget.refactored.di.ApplicationComponent
import com.robotnec.budget.core.mvp.view.AccountsView
import com.robotnec.budget.core.navigator.NavigationBundle
import com.robotnec.budget.core.navigator.Navigator
import com.robotnec.budget.core.persistence.dao.AccountDao
import javax.inject.Inject

/**
 * @author zak zak@swingpulse.com>
 */
class AccountsPresenter(view: AccountsView) : Presenter<AccountsView>(view) {

    @Inject
    internal lateinit var accountDao: AccountDao

    @Inject
    internal lateinit var navigator: Navigator

    override fun injectComponent(applicationComponent: ApplicationComponent) {
        applicationComponent.inject(this)
    }

    override fun onViewResume() {
        view.displayAccounts(accountDao.all)
    }

    fun addOrUpdateAccount(navigationBundle: NavigationBundle<*>) {
        navigator.openAddAccountScreen(navigationBundle)
    }
}
