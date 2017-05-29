package com.robotnec.budget.app.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.robotnec.budget.R
import com.robotnec.budget.app.adapters.AccountsAdapter
import com.robotnec.budget.app.navigator.AndroidNavigationBundle
import com.robotnec.budget.core.domain.Account
import com.robotnec.budget.core.mvp.presenter.AccountsPresenter
import com.robotnec.budget.core.mvp.view.AccountsView
import kotlinx.android.synthetic.main.fragment_accounts.*

/**
 * @author zak zak@swingpulse.com>
 */
class AccountsFragment : BasePresenterFragment<AccountsPresenter>(), AccountsView, AccountsAdapter.AccountClickListener {

    lateinit var accountsAdapter: AccountsAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_accounts
    }

    override fun createPresenter(): AccountsPresenter {
        return AccountsPresenter(this)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbarToggle(toolbar)
        initAccountsList()
        fab.setOnClickListener {
            presenter.addOrUpdateAccount(AndroidNavigationBundle(activity as AppCompatActivity))
        }
    }

    override fun displayAccounts(accounts: List<Account>) {
        accountsAdapter.update(accounts)
    }

    private fun initAccountsList() {
        accountsAdapter = AccountsAdapter(context, this)
        recyclerAccounts.layoutManager = LinearLayoutManager(context)
        recyclerAccounts.adapter = accountsAdapter
    }

    override fun onAccountClick(account: Account) {
        val navigationBundle = AndroidNavigationBundle(activity as AppCompatActivity)
        navigationBundle.serializableExtra = account
        presenter.addOrUpdateAccount(navigationBundle)
    }

    companion object {

        fun newInstance(): Fragment {
            return AccountsFragment()
        }
    }
}
