package com.robotnec.budget.core.di;

import android.content.Context;

import com.robotnec.budget.core.persistence.BudgetDatabase;
import com.robotnec.budget.core.di.module.AndroidModule;
import com.robotnec.budget.core.di.module.DaoModule;
import com.robotnec.budget.core.di.module.NavigationModule;
import com.robotnec.budget.core.navigator.Navigator;

/**
 * @author zak <zak@swingpulse.com>
 */
public class ApplicationGraph {

    private final ApplicationComponent applicationComponent;

    public ApplicationGraph(Navigator navigator,
                            Context context) {
        applicationComponent = DaggerApplicationComponent.builder()
                .daoModule(new DaoModule(new BudgetDatabase(context)))
                .navigationModule(new NavigationModule(navigator))
                .androidModule(new AndroidModule(context))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
