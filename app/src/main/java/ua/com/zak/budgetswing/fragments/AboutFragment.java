package ua.com.zak.budgetswing.fragments;

import ua.com.zak.budgetswing.R;
import ua.com.zak.budgetswing.core.mvp.presenter.AboutPresenter;
import ua.com.zak.budgetswing.core.mvp.view.AboutView;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AboutFragment extends BasePresenterFragment<AboutPresenter> implements AboutView {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_about;
    }

    @Override
    protected AboutPresenter createPresenter() {
        return new AboutPresenter(this);
    }
}
