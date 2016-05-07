package ua.com.zak.budgetswing.core.mvp.presenter;

import javax.inject.Inject;

import ua.com.zak.budgetswing.core.dao.AccountDao;
import ua.com.zak.budgetswing.core.di.ApplicationComponent;
import ua.com.zak.budgetswing.core.domain.Account;
import ua.com.zak.budgetswing.core.mvp.view.AddAccountView;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AddAccountPresenter extends Presenter<AddAccountView> {

    @Inject
    AccountDao mAccountDao;

    public AddAccountPresenter(AddAccountView view) {
        super(view);
    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    @Override
    public void onViewReady() {

    }

    public void addAccount(String accountName, String accountInitialAmount, String accountCurrency) {
        Account account = new Account();
        account.setName(accountName);
        account.setAmount(Integer.valueOf(accountInitialAmount));
        account.setCurrencyCode(accountCurrency);
        mAccountDao.addAccount(account);
    }
}
