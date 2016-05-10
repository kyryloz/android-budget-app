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

    private final Account mAccount;
    private final boolean mEditMode;

    public AddAccountPresenter(AddAccountView view, Account account) {
        super(view);
        mAccount = account;
        mEditMode = mAccount != null;
    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    @Override
    public void onViewReady() {
        if (mEditMode) {
            mView.initEditMode(mAccount);
        }
    }

    public void addOrEditAccount(String accountName, String accountInitialAmount, String accountCurrency) {
        if (mEditMode) {
            mAccount.setName(accountName);
            mAccount.setAmount(Integer.valueOf(accountInitialAmount));
            mAccountDao.updateAccount(mAccount);
        } else {
            Account account = new Account();
            account.setName(accountName);
            account.setAmount(Integer.valueOf(accountInitialAmount));
            account.setCurrencyCode(accountCurrency);
            mAccountDao.addAccount(account);
        }
    }

    public void deleteAccount() {
        mAccountDao.removeAccount(mAccount.getId());
        mView.closeView();
    }
}
