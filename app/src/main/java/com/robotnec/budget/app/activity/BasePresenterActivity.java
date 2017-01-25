package com.robotnec.budget.app.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.robotnec.budget.app.BudgetApplication;
import com.robotnec.budget.core.di.ApplicationGraph;
import com.robotnec.budget.core.mvp.presenter.Presenter;
import com.robotnec.budget.core.mvp.view.View;

import butterknife.ButterKnife;

/**
 * @author zak <zak@swingpulse.com>
 */
public abstract class BasePresenterActivity<P extends Presenter>
        extends AppCompatActivity implements View {

    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        presenter = createPresenter();
        ApplicationGraph applicationGraph = ((BudgetApplication) getApplication())
                .getApplicationGraph();
        presenter.injectComponent(applicationGraph.getApplicationComponent());
        presenter.onViewReady();
    }

    protected abstract P createPresenter();

    protected abstract int getLayoutId();
}
