package ua.com.zak.budgetswing.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.Bind;
import ua.com.zak.budgetswing.R;
import ua.com.zak.budgetswing.core.mvp.presenter.AboutPresenter;
import ua.com.zak.budgetswing.core.mvp.view.AboutView;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AboutFragment extends BasePresenterFragment<AboutPresenter> implements AboutView {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    public static Fragment newInstance() {
        return new AboutFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_about;
    }

    @Override
    protected AboutPresenter createPresenter() {
        return new AboutPresenter(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbarToggle(mToolbar);
    }
}
