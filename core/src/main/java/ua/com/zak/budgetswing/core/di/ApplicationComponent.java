package ua.com.zak.budgetswing.core.di;

import javax.inject.Singleton;

import dagger.Component;
import ua.com.zak.budgetswing.core.mvp.presenter.AccountsPresenter;
import ua.com.zak.budgetswing.core.mvp.presenter.MakeTransactionPresenter;

/**
 * @author zak <zak@swingpulse.com>
 */
@Singleton
@Component(
    modules = DaoModule.class
)
public interface ApplicationComponent {
    void inject(AccountsPresenter presenter);
    void inject(MakeTransactionPresenter presenter);
}
