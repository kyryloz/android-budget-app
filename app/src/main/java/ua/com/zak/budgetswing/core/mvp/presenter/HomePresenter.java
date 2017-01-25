package ua.com.zak.budgetswing.core.mvp.presenter;

import java.util.List;

import javax.inject.Inject;

import ua.com.zak.budgetswing.core.dao.AccountDao;
import ua.com.zak.budgetswing.core.di.ApplicationComponent;
import ua.com.zak.budgetswing.core.domain.Account;
import ua.com.zak.budgetswing.core.mvp.view.HomeView;
import ua.com.zak.budgetswing.core.navigator.NavigationBundle;
import ua.com.zak.budgetswing.core.navigator.Navigator;

/**
 * @author zak <zak@swingpulse.com>
 */
public class HomePresenter extends Presenter<HomeView> {

    @Inject
    AccountDao mAccountDao;

    @Inject
    Navigator mNavigator;

    public HomePresenter(HomeView view) {
        super(view);
    }

    @Override
    public void onViewReady() {
        List<Account> accounts = mAccountDao.getAllAccounts();
        mView.displayAccounts(accounts);
    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    public void addTransaction(NavigationBundle navigationBundle) {
        mNavigator.openAddTransactionScreen(navigationBundle);
    }
}
