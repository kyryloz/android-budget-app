package ua.com.zak.budgetswing.fragments;

import ua.com.zak.budgetswing.R;
import ua.com.zak.budgetswing.core.mvp.presenter.CategoriesPresenter;
import ua.com.zak.budgetswing.core.mvp.view.CategoriesView;

/**
 * @author zak <zak@swingpulse.com>
 */
public class CategoriesFragment extends BasePresenterFragment<CategoriesPresenter> implements CategoriesView {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_categories;
    }

    @Override
    protected CategoriesPresenter createPresenter() {
        return new CategoriesPresenter(this);
    }
}
