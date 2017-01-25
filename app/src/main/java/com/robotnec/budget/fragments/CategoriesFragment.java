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
import com.robotnec.budget.adapters.CategoriesAdapter;
import com.robotnec.budget.core.domain.Category;
import com.robotnec.budget.core.mvp.presenter.CategoriesPresenter;
import com.robotnec.budget.core.mvp.view.CategoriesView;
import com.robotnec.budget.navigator.AndroidNavigationBundle;

/**
 * @author zak <zak@swingpulse.com>
 */
public class CategoriesFragment extends BasePresenterFragment<CategoriesPresenter>
        implements CategoriesView, CategoriesAdapter.CategoryClickListener {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.recycler_categories)
    RecyclerView mRecyclerCategories;

    private CategoriesAdapter mCategoriesAdapter;

    public static Fragment newInstance() {
        return new CategoriesFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_categories;
    }

    @Override
    protected CategoriesPresenter createPresenter() {
        return new CategoriesPresenter(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbarToggle(mToolbar);
        initRecyclerView();
    }

    @OnClick(R.id.fab)
    void onFabAddClicked() {
        mPresenter.addOrUpdateCategory(new AndroidNavigationBundle((AppCompatActivity) getActivity()));
    }

    @Override
    public void displayCategories(List<Category> categories) {
        mCategoriesAdapter.update(categories);
    }

    @Override
    public void onCategoryClick(Category category) {
        AndroidNavigationBundle navigationBundle = new AndroidNavigationBundle((AppCompatActivity) getActivity());
        navigationBundle.setSerializableExtra(category);
        mPresenter.addOrUpdateCategory(navigationBundle);
    }

    private void initRecyclerView() {
        mCategoriesAdapter = new CategoriesAdapter(getContext(), this);
        mRecyclerCategories.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerCategories.setHasFixedSize(true);
        mRecyclerCategories.setAdapter(mCategoriesAdapter);
    }
}
