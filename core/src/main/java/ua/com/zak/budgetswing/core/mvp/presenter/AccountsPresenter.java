package ua.com.zak.budgetswing.core.mvp.presenter;

import javax.inject.Inject;

import ua.com.zak.budgetswing.core.dao.AccountDao;
import ua.com.zak.budgetswing.core.di.ApplicationComponent;
import ua.com.zak.budgetswing.core.mvp.view.AccountsView;
import ua.com.zak.budgetswing.core.navigator.NavigationBundle;
import ua.com.zak.budgetswing.core.navigator.Navigator;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AccountsPresenter extends Presenter<AccountsView> {

    @Inject
    AccountDao mAccountDao;

    @Inject
    Navigator mNavigator;

    public AccountsPresenter(AccountsView view) {
        super(view);
    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    @Override
    public void onViewReady() {
        mView.displayAccounts(mAccountDao.getAllAccounts());
    }

    public void addNewAccount(NavigationBundle navigationBundle) {
        mNavigator.openAddAccountScreen(navigationBundle);
    }
}
