package com.robotnec.budget.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import com.robotnec.budget.BudgetApplication;
import com.robotnec.budget.core.di.ApplicationGraph;
import com.robotnec.budget.core.mvp.presenter.Presenter;
import com.robotnec.budget.core.mvp.view.View;

/**
 * @author zak <zak@swingpulse.com>
 */
public abstract class BasePresenterActivity<P extends Presenter>
        extends AppCompatActivity implements View {

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mPresenter = createPresenter();
        ApplicationGraph applicationGraph = ((BudgetApplication) getApplication())
                .getApplicationGraph();
        mPresenter.injectComponent(applicationGraph.getApplicationComponent());
        mPresenter.onViewReady();
    }

    protected abstract P createPresenter();

    protected abstract int getLayoutId();
}
