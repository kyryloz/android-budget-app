package com.robotnec.budget.core.di;

import javax.inject.Singleton;

import dagger.Component;
import com.robotnec.budget.core.mvp.presenter.AboutPresenter;
import com.robotnec.budget.core.mvp.presenter.AccountsPresenter;
import com.robotnec.budget.core.mvp.presenter.AddAccountPresenter;
import com.robotnec.budget.core.mvp.presenter.AddCategoryPresenter;
import com.robotnec.budget.core.mvp.presenter.AddTransactionPresenter;
import com.robotnec.budget.core.mvp.presenter.CategoriesPresenter;
import com.robotnec.budget.core.mvp.presenter.CategoryOverviewPresenter;
import com.robotnec.budget.core.mvp.presenter.HomePresenter;
import com.robotnec.budget.core.mvp.presenter.MainPresenter;
import com.robotnec.budget.core.mvp.presenter.TransactionsPresenter;

/**
 * @author zak <zak@swingpulse.com>
 */
@Singleton
@Component(
        modules = {
                DaoModule.class,
                NavigationModule.class
        }
)
public interface ApplicationComponent {
    void inject(AccountsPresenter presenter);

    void inject(AddTransactionPresenter presenter);

    void inject(MainPresenter mainPresenter);

    void inject(HomePresenter homePresenter);

    void inject(CategoriesPresenter categoriesPresenter);

    void inject(TransactionsPresenter transactionsPresenter);

    void inject(AboutPresenter aboutPresenter);

    void inject(AddCategoryPresenter addCategoryPresenter);

    void inject(AddAccountPresenter addAccountPresenter);

    void inject(CategoryOverviewPresenter categoryOverviewPresenter);
}
