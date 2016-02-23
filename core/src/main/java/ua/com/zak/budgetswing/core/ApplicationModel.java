package ua.com.zak.budgetswing.core;

import ua.com.zak.budgetswing.core.dao.factory.DaoFactory;

/**
 * @author zak <zak@swingpulse.com>
 */
public class ApplicationModel {

    private final DaoFactory mDaoFactory;
    private final ApplicationComponent mApplicationComponent;

    public ApplicationModel(DaoFactory daoFactory) {
        mDaoFactory = daoFactory;
        mApplicationComponent = DaggerApplicationComponent.builder()
                .daoModule(new DaoModule(daoFactory))
                .build();
    }

    public DaoFactory getDaoFactory() {
        return mDaoFactory;
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
