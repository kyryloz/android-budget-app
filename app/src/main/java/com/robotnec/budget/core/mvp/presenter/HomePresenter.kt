package com.robotnec.budget.core.mvp.presenter

import com.robotnec.budget.core.di.ApplicationComponent
import com.robotnec.budget.core.mvp.view.HomeView
import com.robotnec.budget.core.navigator.NavigationBundle
import com.robotnec.budget.core.navigator.Navigator
import com.robotnec.budget.core.persistence.dao.AccountDao
import com.robotnec.budget.core.persistence.dao.TransactionDao
import com.robotnec.budget.core.service.aggregation.AggregationService
import javax.inject.Inject

/**
 * @author zak zak@swingpulse.com>
 */
class HomePresenter(view: HomeView) : Presenter<HomeView>(view) {

    @Inject
    internal lateinit var accountDao: AccountDao

    @Inject
    internal lateinit var transactionDao: TransactionDao

    @Inject
    internal lateinit var aggregationService: AggregationService

    @Inject
    internal lateinit var navigator: Navigator

    override fun onViewResume() {
        val accounts = accountDao.all
        val aggregation = aggregationService.aggregate(transactionDao.all,
                AggregationService.Resolution.DAY)
        view.displayAccountsWithTransactions(accounts, aggregation)
    }

    override fun injectComponent(applicationComponent: ApplicationComponent) {
        applicationComponent.inject(this)
    }

    fun addTransaction(navigationBundle: NavigationBundle<*>) {
        navigator.openAddTransactionScreen(navigationBundle)
    }
}
