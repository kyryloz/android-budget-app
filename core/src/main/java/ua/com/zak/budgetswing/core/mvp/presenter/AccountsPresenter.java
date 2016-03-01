package ua.com.zak.budgetswing.core.mvp.presenter;

import javax.inject.Inject;

import ua.com.zak.budgetswing.core.dao.AccountDao;
import ua.com.zak.budgetswing.core.dao.CategoryDao;
import ua.com.zak.budgetswing.core.di.ApplicationComponent;
import ua.com.zak.budgetswing.core.mvp.view.AccountsView;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AccountsPresenter extends Presenter<AccountsView> {

    @Inject
    AccountDao mAccountDao;

    @Inject
    CategoryDao mCategoryDao;

    public AccountsPresenter(AccountsView view) {
        super(view);
    }

    @Override
    public void onViewReady() {
        mView.displayAccount(mAccountDao.getAllAccounts());
        mView.displayCategory(mCategoryDao.getAllCategories());
    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }
}
