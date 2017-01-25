package com.robotnec.budget.core.mvp.presenter;

import javax.inject.Inject;

import com.robotnec.budget.core.di.ApplicationComponent;
import com.robotnec.budget.core.mvp.view.MainView;
import com.robotnec.budget.core.navigator.NavigationBundle;
import com.robotnec.budget.core.navigator.Navigator;

/**
 * @author zak <zak@swingpulse.com>
 */
public class MainPresenter extends Presenter<MainView> {

    @Inject
    Navigator mNavigator;

    public MainPresenter(MainView view) {
        super(view);
    }

    @Override
    public void onViewReady() {

    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    public void openMakeTransactionScreen(NavigationBundle navigationBundle) {
        mNavigator.openAddTransactionScreen(navigationBundle);
    }
}
