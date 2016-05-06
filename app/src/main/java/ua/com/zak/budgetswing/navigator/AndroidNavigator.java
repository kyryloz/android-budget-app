package ua.com.zak.budgetswing.navigator;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import ua.com.zak.budgetswing.activity.AddAccountActivity;
import ua.com.zak.budgetswing.activity.AddCategoryActivity;
import ua.com.zak.budgetswing.activity.AddTransactionActivity;
import ua.com.zak.budgetswing.core.navigator.NavigationBundle;
import ua.com.zak.budgetswing.core.navigator.Navigator;

/**
 * @author zak <zak@swingpulse.com>
 */
@SuppressWarnings("unchecked")
public class AndroidNavigator implements Navigator {

    @Override
    public void openAddTransactionScreen(NavigationBundle navigationBundle) {
        AndroidNavigationBundle androidNavigationBundle = ((AndroidNavigationBundle) navigationBundle);
        AppCompatActivity activity = androidNavigationBundle.getNavigationContext();
        Intent intent = new Intent(activity, AddTransactionActivity.class);
        activity.startActivity(
                intent,
                ActivityOptions.makeSceneTransitionAnimation(activity).toBundle());
    }

    @Override
    public void openAddCategoryScreen(NavigationBundle navigationBundle) {
        AndroidNavigationBundle androidNavigationBundle = ((AndroidNavigationBundle) navigationBundle);
        AppCompatActivity activity = androidNavigationBundle.getNavigationContext();
        Intent intent = new Intent(activity, AddCategoryActivity.class);

        activity.startActivity(
                intent,
                ActivityOptions.makeSceneTransitionAnimation(activity).toBundle());
    }

    @Override
    public void openAddAccountScreen(NavigationBundle navigationBundle) {
        AndroidNavigationBundle androidNavigationBundle = ((AndroidNavigationBundle) navigationBundle);
        AppCompatActivity activity = androidNavigationBundle.getNavigationContext();
        Intent intent = new Intent(activity, AddAccountActivity.class);

        activity.startActivity(
                intent,
                ActivityOptions.makeSceneTransitionAnimation(activity).toBundle());
    }
}
