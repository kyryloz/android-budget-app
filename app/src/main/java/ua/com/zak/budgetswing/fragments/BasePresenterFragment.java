package ua.com.zak.budgetswing.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import ua.com.zak.budgetswing.BudgetApplication;
import ua.com.zak.budgetswing.core.di.ApplicationGraph;
import ua.com.zak.budgetswing.core.mvp.presenter.Presenter;
import ua.com.zak.budgetswing.core.mvp.view.MvpView;

/**
 * @author zak <zak@swingpulse.com>
 */
public abstract class BasePresenterFragment<P extends Presenter> extends Fragment implements MvpView {

    protected P mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, root);
        mPresenter = createPresenter();
        ApplicationGraph applicationGraph = ((BudgetApplication) getActivity().getApplication()).getApplicationGraph();
        mPresenter.injectComponent(applicationGraph.getApplicationComponent());
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.bindView();
    }

    protected abstract int getLayoutId();

    protected abstract P createPresenter();
}
