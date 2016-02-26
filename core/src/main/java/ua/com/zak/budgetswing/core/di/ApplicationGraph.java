package ua.com.zak.budgetswing.core.di;

import ua.com.zak.budgetswing.core.dao.factory.DaoFactory;

/**
 * @author zak <zak@swingpulse.com>
 */
public class ApplicationGraph {

    private final ApplicationComponent mApplicationComponent;

    public ApplicationGraph(DaoFactory daoFactory) {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .daoModule(new DaoModule(daoFactory))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
