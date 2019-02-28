package com.robotnec.budget.app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import com.robotnec.budget.R
import com.robotnec.budget.app.adapters.AccountsAdapter
import com.robotnec.budget.app.navigator.AndroidNavigationBundle
import com.robotnec.budget.core.domain.Account
import com.robotnec.budget.core.mvp.presenter.AccountsPresenter
import com.robotnec.budget.core.mvp.view.AccountsView
import kotlinx.android.synthetic.main.fragment_accounts.fab
import kotlinx.android.synthetic.main.fragment_accounts.recyclerAccounts
import kotlinx.android.synthetic.main.fragment_accounts.toolbar

/**
 * @author zak zak@swingpulse.com>
 */
class AccountsFragment : BasePresenterFragment<AccountsPresenter>(), AccountsView, AccountsAdapter.AccountClickListener {

    companion object {
        fun newInstance(): Fragment {
            return AccountsFragment()
        }
    }

    private lateinit var accountsAdapter: AccountsAdapter

    override val layoutId: Int
        get() = R.layout.fragment_accounts

    override fun createPresenter(): AccountsPresenter {
        return AccountsPresenter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbarToggle(toolbar)
        initAccountsList()
        fab.setOnClickListener {
            presenter.addOrUpdateAccount(AndroidNavigationBundle(activity as AppCompatActivity, null))
        }
    }

    override fun displayAccounts(accounts: List<Account>) {
        accountsAdapter.update(accounts)
    }

    private fun initAccountsList() {
        context?.let {
            accountsAdapter = AccountsAdapter(it, this)
            recyclerAccounts.layoutManager = LinearLayoutManager(context)
            recyclerAccounts.adapter = accountsAdapter
        }
    }

    override fun onAccountClick(account: Account) {
        val navigationBundle = AndroidNavigationBundle(activity as AppCompatActivity, account)
        presenter.addOrUpdateAccount(navigationBundle)
    }
}
