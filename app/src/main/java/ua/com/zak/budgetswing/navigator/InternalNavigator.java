package ua.com.zak.budgetswing.navigator;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import ua.com.zak.budgetswing.activity.MakeTransactionActivity;

/**
 * @author zak <zak@swingpulse.com>
 */
@SuppressWarnings("unchecked")
public class InternalNavigator implements Navigator {

    private final AppCompatActivity mAppCompatActivity;

    public InternalNavigator(AppCompatActivity appCompatActivity) {
        mAppCompatActivity = appCompatActivity;
    }

    @Override
    public void openMakeTransactionScreen() {
        Intent intent = new Intent(mAppCompatActivity, MakeTransactionActivity.class);
        mAppCompatActivity.startActivity(
                intent,
                ActivityOptions.makeSceneTransitionAnimation(mAppCompatActivity).toBundle());
    }
}
