package ua.com.zak.budgetswing.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import ua.com.zak.budgetswing.BudgetApplication;
import ua.com.zak.budgetswing.R;
import ua.com.zak.budgetswing.core.di.ApplicationGraph;
import ua.com.zak.budgetswing.core.mvp.presenter.Presenter;
import ua.com.zak.budgetswing.core.mvp.view.View;

/**
 * @author zak <zak@swingpulse.com>
 */
public abstract class BasePresenterFragment<P extends Presenter> extends Fragment implements View {

    protected P mPresenter;

    @Nullable
    @Override
    public android.view.View onCreateView(LayoutInflater inflater,
                                          @Nullable ViewGroup container,
                                          @Nullable Bundle savedInstanceState) {
        android.view.View root = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, root);
        mPresenter = createPresenter();
        ApplicationGraph applicationGraph = ((BudgetApplication) getActivity()
                .getApplication())
                .getApplicationGraph();
        mPresenter.injectComponent(applicationGraph.getApplicationComponent());
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onViewReady();
    }

    protected void initToolbarToggle(Toolbar toolbar) {
        DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    protected void initToolbarBack(Toolbar toolbar) {
        final AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);

        ActionBar supportActionBar = activity.getSupportActionBar();

        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowHomeEnabled(true);
        }

        toolbar.setNavigationOnClickListener(v -> activity.finish());
    }

    protected abstract int getLayoutId();

    protected abstract P createPresenter();
}
