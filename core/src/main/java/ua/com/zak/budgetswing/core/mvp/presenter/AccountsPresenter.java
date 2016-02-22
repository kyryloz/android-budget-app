package ua.com.zak.budgetswing.core.mvp.presenter;

import javax.inject.Inject;

import ua.com.zak.budgetswing.core.DaggerApplicationComponent;
import ua.com.zak.budgetswing.core.dao.AccountDao;
import ua.com.zak.budgetswing.core.mvp.view.AccountsView;
import ua.com.zak.mvpcore.presenter.Presenter;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AccountsPresenter extends Presenter<AccountsView> {

    @Inject
    AccountDao mAccountDao;


    public AccountsPresenter(AccountsView view) {
        super(view);
        DaggerApplicationComponent.create().inject(this);
    }

    @Override
    public void initView() {
        mView.displayAccount(mAccountDao.getAllAccounts());
    }
}
