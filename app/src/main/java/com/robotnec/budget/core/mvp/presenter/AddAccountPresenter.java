package com.robotnec.budget.core.mvp.presenter;

import com.robotnec.budget.core.persistence.dao.AccountDao;
import com.robotnec.budget.core.di.ApplicationComponent;
import com.robotnec.budget.core.domain.Account;
import com.robotnec.budget.core.domain.Currency;
import com.robotnec.budget.core.domain.MoneyAmount;
import com.robotnec.budget.core.mvp.view.AddAccountView;

import javax.inject.Inject;

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
    public void onViewResume() {
        if (editMode) {
            view.initEditMode(account);
        }
    }

    public void addOrEditAccount(String accountName, String accountAmount, String accountCurrency) {
        MoneyAmount amount = MoneyAmount.of(Double.parseDouble(accountAmount),
                Currency.fromCode(accountCurrency));
        if (editMode) {
            account.setName(accountName);
            account.setAmount(amount);
            accountDao.createOrUpdate(account);
        } else {
            Account account = new Account();
            account.setName(accountName);
            account.setAmount(amount);
            accountDao.createOrUpdate(account);
        }
    }

    public void deleteAccount() {
        accountDao.remove(account.getId());
        view.closeView();
    }
}
