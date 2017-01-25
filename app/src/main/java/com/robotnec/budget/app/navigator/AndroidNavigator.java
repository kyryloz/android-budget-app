package com.robotnec.budget.app.navigator;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.robotnec.budget.app.activity.AddAccountActivity;
import com.robotnec.budget.app.activity.AddCategoryActivity;
import com.robotnec.budget.app.activity.AddTransactionActivity;
import com.robotnec.budget.core.domain.Account;
import com.robotnec.budget.core.domain.Category;
import com.robotnec.budget.core.navigator.NavigationBundle;
import com.robotnec.budget.core.navigator.Navigator;
import com.robotnec.budget.app.util.Keys;

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

        Category category = (Category) androidNavigationBundle.getSerializableExtra();

        Intent intent = new Intent(activity, AddCategoryActivity.class);
        intent.putExtra(Keys.CATEGORY, category);

        activity.startActivity(
                intent,
                ActivityOptions.makeSceneTransitionAnimation(activity).toBundle());
    }

    @Override
    public void openAddAccountScreen(NavigationBundle navigationBundle) {
        AndroidNavigationBundle androidNavigationBundle = ((AndroidNavigationBundle) navigationBundle);
        AppCompatActivity activity = androidNavigationBundle.getNavigationContext();

        Account account = (Account) androidNavigationBundle.getSerializableExtra();

        Intent intent = new Intent(activity, AddAccountActivity.class);
        intent.putExtra(Keys.ACCOUNT, account);

        activity.startActivity(
                intent,
                ActivityOptions.makeSceneTransitionAnimation(activity).toBundle());
    }
}
