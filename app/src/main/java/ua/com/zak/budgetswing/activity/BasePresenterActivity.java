package ua.com.zak.budgetswing.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import ua.com.zak.budgetswing.BudgetApplication;
import ua.com.zak.budgetswing.core.di.ApplicationGraph;
import ua.com.zak.budgetswing.core.mvp.presenter.Presenter;
import ua.com.zak.budgetswing.core.mvp.view.MvpView;
import ua.com.zak.budgetswing.navigator.InternalNavigator;
import ua.com.zak.budgetswing.navigator.Navigator;

/**
 * @author zak <zak@swingpulse.com>
 */
public abstract class BasePresenterActivity<P extends Presenter>
        extends AppCompatActivity implements MvpView {

    protected P mPresenter;
    protected Navigator mInternalNavigator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mPresenter = createPresenter();
        ApplicationGraph applicationGraph = ((BudgetApplication) getApplication())
                .getApplicationGraph();
        mPresenter.injectComponent(applicationGraph.getApplicationComponent());
        mInternalNavigator = new InternalNavigator(this);
        mPresenter.bindView();
    }

    protected abstract P createPresenter();

    protected abstract int getLayoutId();
}
