package ua.com.zak.budgetswing.core.mvp.presenter;

import javax.inject.Inject;

import ua.com.zak.budgetswing.core.di.ApplicationComponent;
import ua.com.zak.budgetswing.core.mvp.view.MainView;
import ua.com.zak.budgetswing.core.navigator.NavigationBundle;
import ua.com.zak.budgetswing.core.navigator.Navigator;

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
    public void bindView() {
        mView.initView();
    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    public void openMakeTransactionScreen(NavigationBundle navigationBundle) {
        mNavigator.openMakeTransactionScreen(navigationBundle);
    }
}
