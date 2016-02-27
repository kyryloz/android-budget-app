package ua.com.zak.budgetswing.core.di;

import ua.com.zak.budgetswing.core.dao.factory.DaoFactory;
import ua.com.zak.budgetswing.core.navigator.Navigator;

/**
 * @author zak <zak@swingpulse.com>
 */
public class ApplicationGraph {

    private final ApplicationComponent mApplicationComponent;

    public ApplicationGraph(DaoFactory daoFactory, Navigator navigator) {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .daoModule(new DaoModule(daoFactory))
                .navigationModule(new NavigationModule(navigator))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
