package com.robotnec.budget.core.mvp.presenter;

import java.util.List;

import javax.inject.Inject;

import com.robotnec.budget.core.dao.AccountDao;
import com.robotnec.budget.core.di.ApplicationComponent;
import com.robotnec.budget.core.domain.Account;
import com.robotnec.budget.core.mvp.view.HomeView;
import com.robotnec.budget.core.navigator.NavigationBundle;
import com.robotnec.budget.core.navigator.Navigator;

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
