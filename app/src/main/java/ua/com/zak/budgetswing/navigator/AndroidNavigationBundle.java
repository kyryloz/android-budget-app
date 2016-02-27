package ua.com.zak.budgetswing.navigator;

import android.support.v7.app.AppCompatActivity;

import ua.com.zak.budgetswing.core.navigator.NavigationBundle;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AndroidNavigationBundle implements NavigationBundle<AppCompatActivity> {

    private AppCompatActivity mActivity;

    public AndroidNavigationBundle(AppCompatActivity activity) {
        mActivity = activity;
    }

    @Override
    public AppCompatActivity getNavigationContext() {
        return mActivity;
    }
}
