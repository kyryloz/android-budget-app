package ua.com.zak.budgetswing.core.mvp.presenter;

import javax.inject.Inject;

import ua.com.zak.budgetswing.core.ApplicationComponent;
import ua.com.zak.budgetswing.core.dao.AccountDao;
import ua.com.zak.budgetswing.core.dao.CategoryDao;
import ua.com.zak.budgetswing.core.mvp.view.AccountsView;
import ua.com.zak.mvpcore.presenter.Presenter;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AccountsPresenter extends Presenter<AccountsView> {

    @Inject
    AccountDao mAccountDao;

    @Inject
    CategoryDao mCategoryDao;

    public AccountsPresenter(AccountsView view, ApplicationComponent applicationComponent) {
        super(view, applicationComponent);
    }

    @Override
    public void initView() {
        mView.displayAccount(mAccountDao.getAllAccounts());
        mView.displayCategory(mCategoryDao.getAllCategories());
    }
}
