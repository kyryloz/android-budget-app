package ua.com.zak.budgetswing.fragments;

import android.support.v4.app.Fragment;

import ua.com.zak.budgetswing.R;
import ua.com.zak.budgetswing.core.mvp.presenter.HomePresenter;
import ua.com.zak.budgetswing.core.mvp.view.HomeView;

/**
 * @author zak <zak@swingpulse.com>
 */
public class HomeFragment extends BasePresenterFragment<HomePresenter> implements HomeView {

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
}
