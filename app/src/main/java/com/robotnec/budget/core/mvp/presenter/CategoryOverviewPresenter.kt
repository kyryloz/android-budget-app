package com.robotnec.budget.core.mvp.presenter

import com.robotnec.budget.refactored.di.ApplicationComponent
import com.robotnec.budget.core.domain.Category
import com.robotnec.budget.core.mvp.view.CategoryOverviewView
import com.robotnec.budget.core.persistence.dao.TransactionDao
import com.robotnec.budget.core.service.aggregation.AggregationService
import javax.inject.Inject

/**
 * @author zak zak@swingpulse.com>
 */
class CategoryOverviewPresenter(view: CategoryOverviewView, private val category: Category) : Presenter<CategoryOverviewView>(view) {

    @Inject
    internal lateinit var transactionDao: TransactionDao

    @Inject
    internal lateinit var aggregationService: AggregationService

    override fun injectComponent(applicationComponent: ApplicationComponent) {
        applicationComponent.inject(this)
    }

    override fun onViewResume() {
        val categoryTransactions = transactionDao.getTransactionsForCategory(category.id)
        val aggregation = aggregationService.aggregate(categoryTransactions, AggregationService.Resolution.DAY)
        view.displayCategoryTransactions(aggregation)
        view.displayCategoryTitle(category.name)
    }
}
