package ua.com.zak.budgetswing.core.di;

import javax.inject.Singleton;

import dagger.Component;
import ua.com.zak.budgetswing.core.mvp.presenter.AboutPresenter;
import ua.com.zak.budgetswing.core.mvp.presenter.AccountsPresenter;
import ua.com.zak.budgetswing.core.mvp.presenter.AddCategoryPresenter;
import ua.com.zak.budgetswing.core.mvp.presenter.CategoriesPresenter;
import ua.com.zak.budgetswing.core.mvp.presenter.HomePresenter;
import ua.com.zak.budgetswing.core.mvp.presenter.MainPresenter;
import ua.com.zak.budgetswing.core.mvp.presenter.AddTransactionPresenter;
import ua.com.zak.budgetswing.core.mvp.presenter.TransactionsPresenter;

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
}
