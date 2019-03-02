package com.robotnec.budget.core.mvp.presenter

import com.robotnec.budget.refactored.di.ApplicationComponent
import com.robotnec.budget.core.mvp.view.TransactionsView
import com.robotnec.budget.core.navigator.NavigationBundle
import com.robotnec.budget.core.navigator.Navigator
import com.robotnec.budget.core.persistence.dao.TransactionDao
import com.robotnec.budget.core.service.aggregation.AggregationService
import javax.inject.Inject

/**
 * @author zak zak@swingpulse.com>
 */
class TransactionsPresenter(view: TransactionsView) : Presenter<TransactionsView>(view) {

    @Inject
    internal lateinit var navigator: Navigator

    @Inject
    internal lateinit var transactionDao: TransactionDao

    @Inject
    internal lateinit var aggregator: AggregationService

    override fun onViewResume() {
        val transactions = transactionDao.all
        val aggregation = aggregator.aggregate(transactions, AggregationService.Resolution.DAY)
        view.displayTransactions(aggregation)
    }

    override fun injectComponent(applicationComponent: ApplicationComponent) {
        applicationComponent.inject(this)
    }

    fun addNewTransaction(navigationBundle: NavigationBundle<*>) {
//        navigator.openAddTransactionScreen()
    }
}
