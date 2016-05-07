package ua.com.zak.budgetswing.fragments;

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
import ua.com.zak.budgetswing.R;
import ua.com.zak.budgetswing.adapters.CategoriesAdapter;
import ua.com.zak.budgetswing.adapters.TransactionsAdapter;
import ua.com.zak.budgetswing.core.domain.Category;
import ua.com.zak.budgetswing.core.domain.Transaction;
import ua.com.zak.budgetswing.core.mvp.presenter.TransactionsPresenter;
import ua.com.zak.budgetswing.core.mvp.view.TransactionsView;
import ua.com.zak.budgetswing.navigator.AndroidNavigationBundle;

/**
 * @author zak <zak@swingpulse.com>
 */
public class TransactionsFragment extends BasePresenterFragment<TransactionsPresenter> implements TransactionsView {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.recycler_transactions)
    RecyclerView mRecyclerTransactions;

    private TransactionsAdapter mTransactionsAdapter;

    public static Fragment newInstance() {
        return new TransactionsFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_transactions;
    }

    @Override
    protected TransactionsPresenter createPresenter() {
        return new TransactionsPresenter(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbarToggle(mToolbar);
        initRecyclerView();
    }

    @OnClick(R.id.fab)
    void onFabAddClicked() {
        mPresenter.addNewTransaction(new AndroidNavigationBundle((AppCompatActivity) getActivity()));
    }

    @Override
    public void displayTransactions(List<Transaction> transactions) {
        mTransactionsAdapter.update(transactions);
    }

    private void initRecyclerView() {
        mTransactionsAdapter = new TransactionsAdapter(getContext());
        mRecyclerTransactions.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerTransactions.setHasFixedSize(true);
        mRecyclerTransactions.setAdapter(mTransactionsAdapter);
    }
}
