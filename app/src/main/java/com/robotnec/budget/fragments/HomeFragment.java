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
import com.robotnec.budget.core.mvp.presenter.HomePresenter;
import com.robotnec.budget.core.mvp.view.HomeView;
import com.robotnec.budget.navigator.AndroidNavigationBundle;

/**
 * @author zak <zak@swingpulse.com>
 */
public class HomeFragment extends BasePresenterFragment<HomePresenter> implements HomeView {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.recycler_accounts)
    RecyclerView recyclerAccounts;

    private AccountsAdapter accountsAdapter;

    public static Fragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbarToggle(toolbar);
        initAccountsList();
    }

    @OnClick(R.id.fab)
    void onFabAddClicked() {
        presenter.addTransaction(new AndroidNavigationBundle((AppCompatActivity) getActivity()));
    }

    @Override
    public void displayAccounts(List<Account> accounts) {
        accountsAdapter.update(accounts);
    }

    private void initAccountsList() {
        accountsAdapter = new AccountsAdapter(getContext(), null);
        recyclerAccounts.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerAccounts.setHasFixedSize(true);
        recyclerAccounts.setAdapter(accountsAdapter);
    }
}
