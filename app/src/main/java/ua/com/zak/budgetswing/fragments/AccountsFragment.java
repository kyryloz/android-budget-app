package ua.com.zak.budgetswing.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.Bind;
import ua.com.zak.budgetswing.R;
import ua.com.zak.budgetswing.adapters.AccountsAdapter;
import ua.com.zak.budgetswing.core.domen.Account;
import ua.com.zak.budgetswing.core.mvp.presenter.AccountsPresenter;
import ua.com.zak.budgetswing.core.mvp.view.AccountsView;
import ua.com.zak.mvpandroid.fragment.BasePresenterFragment;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AccountsFragment extends BasePresenterFragment<AccountsPresenter> implements AccountsView {

    @Bind(R.id.recycler_accounts)
    RecyclerView mRecyclerAccounts;

    private AccountsAdapter mAccountsAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_accounts_summary;
    }

    @Override
    protected AccountsPresenter createPresenter() {
        return new AccountsPresenter(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initAccountsList();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initAccountsList() {
        mAccountsAdapter = new AccountsAdapter(getContext());
        mRecyclerAccounts.setAdapter(mAccountsAdapter);
        mRecyclerAccounts.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void displayAccount(List<Account> accounts) {
        mAccountsAdapter.update(accounts);
    }
}
