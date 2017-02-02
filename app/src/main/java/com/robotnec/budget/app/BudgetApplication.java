package com.robotnec.budget.app;

import android.app.Application;

import com.robotnec.budget.app.navigator.AndroidNavigator;
import com.robotnec.budget.core.di.ApplicationGraph;

/**
 * @author zak <zak@swingpulse.com>
 */
public class BudgetApplication extends Application {

    private ApplicationGraph applicationGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationGraph = new ApplicationGraph(
                new AndroidNavigator(),
                this);
    }

    public ApplicationGraph getApplicationGraph() {
        return applicationGraph;
    }
}
