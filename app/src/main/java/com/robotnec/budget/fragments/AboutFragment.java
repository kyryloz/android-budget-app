package com.robotnec.budget.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.Bind;
import com.robotnec.budget.R;
import com.robotnec.budget.core.mvp.presenter.AboutPresenter;
import com.robotnec.budget.core.mvp.view.AboutView;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AboutFragment extends BasePresenterFragment<AboutPresenter> implements AboutView {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

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
        initToolbarToggle(toolbar);
    }
}
