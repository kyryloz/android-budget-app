package ua.com.zak.budgetswing;

import android.app.Application;

import ua.com.zak.budgetswing.core.ApplicationModel;
import ua.com.zak.budgetswing.core.dao.factory.SimpleDaoFactory;

/**
 * @author zak <zak@swingpulse.com>
 */
public class BudgetApplication extends Application {

    private ApplicationModel mApplicationModel;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationModel = new ApplicationModel(new SimpleDaoFactory());
    }

    public ApplicationModel getApplicationModel() {
        return mApplicationModel;
    }
}
