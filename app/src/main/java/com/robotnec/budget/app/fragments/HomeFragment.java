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
import com.robotnec.budget.app.adapters.transaction.TransactionsAdapter;
import com.robotnec.budget.core.domain.Account;
import com.robotnec.budget.core.mvp.presenter.HomePresenter;
import com.robotnec.budget.core.mvp.view.HomeView;
import com.robotnec.budget.app.navigator.AndroidNavigationBundle;
import com.robotnec.budget.core.service.aggregation.impl.TransactionAggregation;

/**
 * @author zak <zak@swingpulse.com>
 */
public class HomeFragment extends BasePresenterFragment<HomePresenter> implements HomeView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recycler_accounts)
    RecyclerView recyclerAccounts;

    @BindView(R.id.recycler_transactions)
    RecyclerView recyclerTransactions;

    private AccountsAdapter accountsAdapter;
    private TransactionsAdapter transactionsAdapter;

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
        initTransactionsList();
    }

    @OnClick(R.id.fab)
    void onFabAddClicked() {
        presenter.addTransaction(new AndroidNavigationBundle((AppCompatActivity) getActivity()));
    }

    @Override
    public void displayAccounts(List<Account> accounts) {
        accountsAdapter.update(accounts);
    }

    @Override
    public void displayTransactions(TransactionAggregation aggregation) {
        transactionsAdapter.setItems(aggregation);
    }

    private void initAccountsList() {
        accountsAdapter = new AccountsAdapter(getContext(), null);
        recyclerAccounts.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerAccounts.setHasFixedSize(true);
        recyclerAccounts.setAdapter(accountsAdapter);
    }

    private void initTransactionsList() {
        transactionsAdapter = new TransactionsAdapter(getContext());
        recyclerTransactions.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerTransactions.setHasFixedSize(true);
        recyclerTransactions.setAdapter(transactionsAdapter);
    }
}
