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

import butterknife.BindView;
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

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recycler_categories)
    RecyclerView recyclerCategories;

    private CategoriesAdapter categoriesAdapter;

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
        initToolbarToggle(toolbar);
        initRecyclerView();
    }

    @OnClick(R.id.fab)
    void onFabAddClicked() {
        presenter.addOrUpdateCategory(new AndroidNavigationBundle((AppCompatActivity) getActivity()));
    }

    @Override
    public void displayCategories(List<Category> categories) {
        categoriesAdapter.update(categories);
    }

    @Override
    public void onCategoryClick(Category category) {
        AndroidNavigationBundle navigationBundle = new AndroidNavigationBundle((AppCompatActivity) getActivity());
        navigationBundle.setSerializableExtra(category);
        presenter.addOrUpdateCategory(navigationBundle);
    }

    private void initRecyclerView() {
        categoriesAdapter = new CategoriesAdapter(getContext(), this);
        recyclerCategories.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerCategories.setHasFixedSize(true);
        recyclerCategories.setAdapter(categoriesAdapter);
    }
}
