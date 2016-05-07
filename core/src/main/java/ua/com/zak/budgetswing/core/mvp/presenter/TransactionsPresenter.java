package ua.com.zak.budgetswing.core.mvp.presenter;

import javax.inject.Inject;

import ua.com.zak.budgetswing.core.di.ApplicationComponent;
import ua.com.zak.budgetswing.core.mvp.view.TransactionsView;
import ua.com.zak.budgetswing.core.navigator.NavigationBundle;
import ua.com.zak.budgetswing.core.navigator.Navigator;

/**
 * @author zak <zak@swingpulse.com>
 */
public class TransactionsPresenter extends Presenter<TransactionsView> {

    @Inject
    Navigator mNavigator;

    public TransactionsPresenter(TransactionsView view) {
        super(view);
    }

    @Override
    public void onViewReady() {

    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    public void addNewTransaction(NavigationBundle navigationBundle) {
        mNavigator.openAddTransactionScreen(navigationBundle);
    }
}
