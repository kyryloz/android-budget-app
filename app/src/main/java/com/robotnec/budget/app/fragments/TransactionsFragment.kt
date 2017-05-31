package com.robotnec.budget.app.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.robotnec.budget.R
import com.robotnec.budget.app.adapters.transaction.TransactionsAdapter
import com.robotnec.budget.app.navigator.AndroidNavigationBundle
import com.robotnec.budget.core.mvp.presenter.TransactionsPresenter
import com.robotnec.budget.core.mvp.view.TransactionsView
import com.robotnec.budget.core.service.aggregation.impl.TransactionAggregation
import kotlinx.android.synthetic.main.fragment_transactions.*

/**
 * @author zak zak@swingpulse.com>
 */
class TransactionsFragment : BasePresenterFragment<TransactionsPresenter>(), TransactionsView {

    private lateinit var transactionsAdapter: TransactionsAdapter

    override val layoutId: Int
        get() = R.layout.fragment_transactions

    override fun createPresenter(): TransactionsPresenter {
        return TransactionsPresenter(this)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbarToggle(toolbar!!)
        initRecyclerView()
        fab.setOnClickListener {
            presenter.addNewTransaction(AndroidNavigationBundle(activity as AppCompatActivity))
        }
    }

    override fun displayTransactions(aggregation: TransactionAggregation) {
        transactionsAdapter.update(aggregation)
    }

    private fun initRecyclerView() {
        transactionsAdapter = TransactionsAdapter(context)
        recyclerTransactions.layoutManager = LinearLayoutManager(context)
        recyclerTransactions.adapter = transactionsAdapter
    }

    companion object {

        fun newInstance(): Fragment {
            return TransactionsFragment()
        }
    }
}
