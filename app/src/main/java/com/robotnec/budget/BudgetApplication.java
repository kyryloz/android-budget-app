package com.robotnec.budget;

import android.app.Application;

import com.robotnec.budget.core.dao.factory.SimpleDaoFactory;
import com.robotnec.budget.core.di.ApplicationGraph;
import com.robotnec.budget.navigator.AndroidNavigator;

/**
 * @author zak <zak@swingpulse.com>
 */
public class BudgetApplication extends Application {

    private ApplicationGraph applicationGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationGraph = new ApplicationGraph(
                new SimpleDaoFactory(),
                new AndroidNavigator()
        );
    }

    public ApplicationGraph getApplicationGraph() {
        return applicationGraph;
    }
}
