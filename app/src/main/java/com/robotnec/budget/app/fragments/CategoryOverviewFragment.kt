package com.robotnec.budget.app.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.robotnec.budget.R
import com.robotnec.budget.app.adapters.transaction.TransactionsAdapter
import com.robotnec.budget.app.util.Keys
import com.robotnec.budget.core.domain.Category
import com.robotnec.budget.core.mvp.presenter.CategoryOverviewPresenter
import com.robotnec.budget.core.mvp.view.CategoryOverviewView
import com.robotnec.budget.core.service.aggregation.impl.TransactionAggregation
import kotlinx.android.synthetic.main.fragment_category_overview.collapsingToolbar
import kotlinx.android.synthetic.main.fragment_category_overview.recyclerCategoryTransactions
import kotlinx.android.synthetic.main.fragment_category_overview.toolbar

/**
 * @author zak zak@swingpulse.com>
 */

class CategoryOverviewFragment : BasePresenterFragment<CategoryOverviewPresenter>(), CategoryOverviewView {

    private lateinit var categoryTransactionsAdapter: TransactionsAdapter

    override val layoutId: Int
        get() = R.layout.fragment_category_overview

    override fun createPresenter(): CategoryOverviewPresenter {
        val category = arguments?.getSerializable(Keys.CATEGORY) as Category
        return CategoryOverviewPresenter(this, category)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbarBack(toolbar)
        initCategoryTransactionsList()
    }

    override fun displayCategoryTitle(title: String) {
        collapsingToolbar.title = title
    }

    override fun displayCategoryTransactions(transactionAggregation: TransactionAggregation) {
        categoryTransactionsAdapter.update(transactionAggregation)
    }

    private fun initCategoryTransactionsList() {
        context?.let {
            categoryTransactionsAdapter = TransactionsAdapter(it)
            recyclerCategoryTransactions.layoutManager = LinearLayoutManager(context)
            recyclerCategoryTransactions.adapter = categoryTransactionsAdapter
        }

    }

    companion object {

        fun newInstance(category: Category): CategoryOverviewFragment {
            val fragment = CategoryOverviewFragment()
            val args = Bundle()
            args.putSerializable(Keys.CATEGORY, category)
            fragment.arguments = args
            return fragment
        }
    }
}
