package ua.com.zak.budgetswing;

import android.app.Application;

import ua.com.zak.budgetswing.core.dao.factory.SimpleDaoFactory;
import ua.com.zak.budgetswing.core.di.ApplicationGraph;

/**
 * @author zak <zak@swingpulse.com>
 */
public class BudgetApplication extends Application {

    private ApplicationGraph mApplicationGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationGraph = new ApplicationGraph(new SimpleDaoFactory());
    }

    public ApplicationGraph getApplicationGraph() {
        return mApplicationGraph;
    }
}
