package com.robotnec.budget.core.mvp.presenter;

import javax.inject.Inject;

import com.robotnec.budget.core.dao.AccountDao;
import com.robotnec.budget.core.di.ApplicationComponent;
import com.robotnec.budget.core.mvp.view.AccountsView;
import com.robotnec.budget.core.navigator.NavigationBundle;
import com.robotnec.budget.core.navigator.Navigator;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AccountsPresenter extends Presenter<AccountsView> {

    @Inject
    AccountDao accountDao;

    @Inject
    Navigator navigator;

    public AccountsPresenter(AccountsView view) {
        super(view);
    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    @Override
    public void onViewReady() {
        view.displayAccounts(accountDao.getAll());
    }

    public void addOrUpdateAccount(NavigationBundle navigationBundle) {
        navigator.openAddAccountScreen(navigationBundle);
    }
}
