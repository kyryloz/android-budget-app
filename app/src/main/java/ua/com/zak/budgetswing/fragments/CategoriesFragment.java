package ua.com.zak.budgetswing.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import ua.com.zak.budgetswing.R;
import ua.com.zak.budgetswing.adapters.CategoriesAdapter;
import ua.com.zak.budgetswing.core.domain.Category;
import ua.com.zak.budgetswing.core.mvp.presenter.CategoriesPresenter;
import ua.com.zak.budgetswing.core.mvp.view.CategoriesView;

/**
 * @author zak <zak@swingpulse.com>
 */
public class CategoriesFragment extends BasePresenterFragment<CategoriesPresenter> implements CategoriesView {

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
    void onFabAddTransactionClicked() {
        mPresenter.addNewCategory();
    }

    @Override
    public void displayCategories(List<Category> categories) {
        mCategoriesAdapter.update(categories);
    }

    private void initRecyclerView() {
        mCategoriesAdapter = new CategoriesAdapter(getContext());
        mRecyclerCategories.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerCategories.setHasFixedSize(true);
        mRecyclerCategories.setAdapter(mCategoriesAdapter);
    }
}
