package com.robotnec.budget.refactored.screens.home

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.robotnec.budget.R
import com.robotnec.budget.app.adapters.HomeHeader
import com.robotnec.budget.app.adapters.support.HeaderAdapter
import com.robotnec.budget.app.adapters.transaction.TransactionsAdapter
import com.robotnec.budget.core.navigator.Navigator
import com.robotnec.budget.refactored.di.helpers.Injectable
import com.robotnec.budget.refactored.extensions.getViewModel
import com.robotnec.budget.refactored.screens.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

/**
 * @author zak zak@swingpulse.com>
 */
@Injectable
class HomeFragment : BaseFragment() {
    override val layoutId = R.layout.fragment_home

    private lateinit var headerAdapter: HeaderAdapter
    private lateinit var transactionsAdapter: TransactionsAdapter
    private lateinit var homeHeader: HomeHeader

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: HomeViewModel

    override fun initView(savedInstanceState: Bundle?) {
        initRecyclerView()

        viewModel = getViewModel(viewModelFactory, HomeViewModel::class)

        viewModel.getAccounts().observe(this, Observer { accounts ->
            homeHeader.update(accounts)
        })

        viewModel.getAggregatedTransactions().observe(this, Observer { aggregation ->
            transactionsAdapter.update(aggregation)
            headerAdapter.notifyDataSetChanged()
        })

        fab.setOnClickListener {
            navigator.openAddTransactionScreen(findNavController())
        }
    }

    private fun initRecyclerView() {
        transactionsAdapter = TransactionsAdapter()
        homeHeader = HomeHeader()
        headerAdapter = HeaderAdapter(transactionsAdapter, homeHeader)

        homeRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = HeaderAdapter(transactionsAdapter, homeHeader)
            setHasFixedSize(true)
        }
    }
}
