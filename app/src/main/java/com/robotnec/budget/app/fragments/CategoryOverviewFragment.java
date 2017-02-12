package com.robotnec.budget.app.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.robotnec.budget.R;
import com.robotnec.budget.app.adapters.transaction.TransactionsAdapter;
import com.robotnec.budget.app.util.Keys;
import com.robotnec.budget.core.domain.Category;
import com.robotnec.budget.core.mvp.presenter.CategoryOverviewPresenter;
import com.robotnec.budget.core.mvp.view.CategoryOverviewView;
import com.robotnec.budget.core.service.aggregation.impl.TransactionAggregation;

import butterknife.BindView;

/**
 * @author zak <zak@swingpulse.com>
 */

public class CategoryOverviewFragment extends BasePresenterFragment<CategoryOverviewPresenter>
        implements CategoryOverviewView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout toolbarLayout;

    @BindView(R.id.recycler_category_transactions)
    RecyclerView recyclerCategoryTransactions;

    TransactionsAdapter categoryTransactionsAdapter;

    public static CategoryOverviewFragment newInstance(Category category) {
        CategoryOverviewFragment fragment = new CategoryOverviewFragment();
        Bundle args = new Bundle();
        args.putSerializable(Keys.CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_category_overview;
    }

    @Override
    protected CategoryOverviewPresenter createPresenter() {
        Category category = (Category) getArguments().getSerializable(Keys.CATEGORY);
        return new CategoryOverviewPresenter(this, category);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbarBack(toolbar);
        initCategoryTransactionsList();
    }

    @Override
    public void displayCategoryTitle(String title) {
        toolbarLayout.setTitle(title);
    }

    @Override
    public void displayCategoryTransactions(TransactionAggregation transactionAggregation) {
        categoryTransactionsAdapter.update(transactionAggregation);
    }

    private void initCategoryTransactionsList() {
        categoryTransactionsAdapter = new TransactionsAdapter(getContext());
        recyclerCategoryTransactions.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerCategoryTransactions.setHasFixedSize(true);
        recyclerCategoryTransactions.setAdapter(categoryTransactionsAdapter);
    }
}
