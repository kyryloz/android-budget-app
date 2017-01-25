package com.robotnec.budget.app.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import com.robotnec.budget.R;
import com.robotnec.budget.app.adapters.AccountsAdapter;
import com.robotnec.budget.core.domain.Account;
import com.robotnec.budget.core.mvp.presenter.AccountsPresenter;
import com.robotnec.budget.core.mvp.view.AccountsView;
import com.robotnec.budget.app.navigator.AndroidNavigationBundle;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AccountsFragment extends BasePresenterFragment<AccountsPresenter> implements AccountsView, AccountsAdapter.AccountClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recycler_accounts)
    RecyclerView recyclerAccounts;

    private AccountsAdapter accountsAdapter;

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
        initToolbarToggle(toolbar);
        initAccountsList();
    }

    @OnClick(R.id.fab)
    void onFabAddClicked() {
        presenter.addOrUpdateAccount(new AndroidNavigationBundle((AppCompatActivity) getActivity()));
    }

    @Override
    public void displayAccounts(List<Account> accounts) {
        accountsAdapter.update(accounts);
    }

    private void initAccountsList() {
        accountsAdapter = new AccountsAdapter(getContext(), this);
        recyclerAccounts.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerAccounts.setHasFixedSize(true);
        recyclerAccounts.setAdapter(accountsAdapter);
    }

    @Override
    public void onAccountClick(Account account) {
        AndroidNavigationBundle navigationBundle = new AndroidNavigationBundle((AppCompatActivity) getActivity());
        navigationBundle.setSerializableExtra(account);
        presenter.addOrUpdateAccount(navigationBundle);
    }
}
