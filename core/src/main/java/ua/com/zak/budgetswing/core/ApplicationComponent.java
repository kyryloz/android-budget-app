package ua.com.zak.budgetswing.core;

import javax.inject.Singleton;

import dagger.Component;
import ua.com.zak.budgetswing.core.mvp.presenter.AccountsPresenter;

/**
 * @author zak <zak@swingpulse.com>
 */
@Singleton
@Component(
    modules = DaoModule.class
)
public interface ApplicationComponent {
    void inject(AccountsPresenter presenter);
}
