package com.robotnec.budget.app.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.robotnec.budget.R
import com.robotnec.budget.app.adapters.HomeHeader
import com.robotnec.budget.app.adapters.support.HeaderAdapter
import com.robotnec.budget.app.adapters.transaction.TransactionsAdapter
import com.robotnec.budget.app.navigator.AndroidNavigationBundle
import com.robotnec.budget.core.domain.Account
import com.robotnec.budget.core.mvp.presenter.HomePresenter
import com.robotnec.budget.core.mvp.view.HomeView
import com.robotnec.budget.core.service.aggregation.impl.TransactionAggregation
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * @author zak zak@swingpulse.com>
 */
class HomeFragment : BasePresenterFragment<HomePresenter>(), HomeView {

    private lateinit var headerAdapter: HeaderAdapter
    private lateinit var transactionsAdapter: TransactionsAdapter
    private lateinit var homeHeader: HomeHeader

    override val layoutId: Int
        get() = R.layout.fragment_home

    override fun createPresenter(): HomePresenter {
        return HomePresenter(this)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbarToggle(toolbar)
        initRecyclerView()
        fab.setOnClickListener {
            presenter.addTransaction(AndroidNavigationBundle(activity as AppCompatActivity, null))
        }
    }

    override fun displayAccountsWithTransactions(accounts: List<Account>,
                                                 aggregation: TransactionAggregation) {
        homeHeader.update(accounts)
        transactionsAdapter.update(aggregation)
        headerAdapter.notifyDataSetChanged()
    }

    private fun initRecyclerView() {
        transactionsAdapter = TransactionsAdapter(context)
        homeHeader = HomeHeader()
        headerAdapter = HeaderAdapter(transactionsAdapter, homeHeader)
        recyclerMerge.layoutManager = LinearLayoutManager(context)
        recyclerMerge.adapter = headerAdapter
    }
}
