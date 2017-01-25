package com.robotnec.budget.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import com.robotnec.budget.R;
import com.robotnec.budget.adapters.AccountsAdapter;
import com.robotnec.budget.core.domain.Account;
import com.robotnec.budget.core.mvp.presenter.AccountsPresenter;
import com.robotnec.budget.core.mvp.view.AccountsView;
import com.robotnec.budget.navigator.AndroidNavigationBundle;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AccountsFragment extends BasePresenterFragment<AccountsPresenter> implements AccountsView, AccountsAdapter.AccountClickListener {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.recycler_accounts)
    RecyclerView mRecyclerAccounts;

    private AccountsAdapter mAccountsAdapter;

    public static Fragment newInstance() {
        return new AccountsFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_accounts;
    }

    @Override
    protected AccountsPresenter createPresenter() {
        return new AccountsPresenter(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbarToggle(mToolbar);
        initAccountsList();
    }

    @OnClick(R.id.fab)
    void onFabAddClicked() {
        mPresenter.addOrUpdateAccount(new AndroidNavigationBundle((AppCompatActivity) getActivity()));
    }

    @Override
    public void displayAccounts(List<Account> accounts) {
        mAccountsAdapter.update(accounts);
    }

    private void initAccountsList() {
        mAccountsAdapter = new AccountsAdapter(getContext(), this);
        mRecyclerAccounts.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerAccounts.setHasFixedSize(true);
        mRecyclerAccounts.setAdapter(mAccountsAdapter);
    }

    @Override
    public void onAccountClick(Account account) {
        AndroidNavigationBundle navigationBundle = new AndroidNavigationBundle((AppCompatActivity) getActivity());
        navigationBundle.setSerializableExtra(account);
        mPresenter.addOrUpdateAccount(navigationBundle);
    }
}
