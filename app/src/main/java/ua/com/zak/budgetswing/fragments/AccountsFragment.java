package ua.com.zak.budgetswing.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import ua.com.zak.budgetswing.R;
import ua.com.zak.budgetswing.adapters.AccountsAdapter;
import ua.com.zak.budgetswing.core.domain.Account;
import ua.com.zak.budgetswing.core.mvp.presenter.AccountsPresenter;
import ua.com.zak.budgetswing.core.mvp.view.AccountsView;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AccountsFragment extends BasePresenterFragment<AccountsPresenter> implements AccountsView {

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
        initAccountsList();
    }

    @OnClick(R.id.fab)
    void onFabAddTransactionClicked() {
        mPresenter.addNewAccount();
    }

    @Override
    public void displayAccounts(List<Account> accounts) {
        mAccountsAdapter.update(accounts);
    }

    private void initAccountsList() {
        mAccountsAdapter = new AccountsAdapter(getContext());
        mRecyclerAccounts.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerAccounts.setHasFixedSize(true);
        mRecyclerAccounts.setAdapter(mAccountsAdapter);
    }
}
