package ua.com.zak.budgetswing.core;

import javax.inject.Singleton;

import dagger.Component;
import ua.com.zak.budgetswing.core.mvp.presenter.AccountsPresenter;
import ua.com.zak.mvpcore.presenter.BaseApplicationComponent;

/**
 * @author zak <zak@swingpulse.com>
 */
@Singleton
@Component(
    modules = { DaoModule.class, DaoFactoryModule.class }
)
public interface ApplicationComponent extends BaseApplicationComponent<AccountsPresenter> {
    void inject(AccountsPresenter presenter);
}
