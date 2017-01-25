package com.robotnec.budget.core.mvp.presenter;

import javax.inject.Inject;

import com.robotnec.budget.core.dao.AccountDao;
import com.robotnec.budget.core.di.ApplicationComponent;
import com.robotnec.budget.core.domain.Account;
import com.robotnec.budget.core.mvp.view.AddAccountView;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AddAccountPresenter extends Presenter<AddAccountView> {

    @Inject
    AccountDao accountDao;

    private final Account account;
    private final boolean editMode;

    public AddAccountPresenter(AddAccountView view, Account account) {
        super(view);
        this.account = account;
        editMode = this.account != null;
    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    @Override
    public void onViewReady() {
        if (editMode) {
            mView.initEditMode(account);
        }
    }

    public void addOrEditAccount(String accountName, String accountInitialAmount, String accountCurrency) {
        if (editMode) {
            account.setName(accountName);
            account.setAmount(Integer.valueOf(accountInitialAmount));
            accountDao.updateAccount(account);
        } else {
            Account account = new Account();
            account.setName(accountName);
            account.setAmount(Integer.valueOf(accountInitialAmount));
            account.setCurrencyCode(accountCurrency);
            accountDao.addAccount(account);
        }
    }

    public void deleteAccount() {
        accountDao.removeAccount(account.getId());
        mView.closeView();
    }
}