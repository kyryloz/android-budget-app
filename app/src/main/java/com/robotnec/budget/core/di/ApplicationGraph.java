package com.robotnec.budget.core.di;

import com.robotnec.budget.core.dao.factory.DaoFactory;
import com.robotnec.budget.core.navigator.Navigator;

/**
 * @author zak <zak@swingpulse.com>
 */
public class ApplicationGraph {

    private final ApplicationComponent applicationComponent;

    public ApplicationGraph(DaoFactory daoFactory, Navigator navigator) {
        applicationComponent = DaggerApplicationComponent.builder()
                .daoModule(new DaoModule(daoFactory))
                .navigationModule(new NavigationModule(navigator))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
