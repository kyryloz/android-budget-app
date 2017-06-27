package com.robotnec.budget.app.navigator

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.support.v7.app.AppCompatActivity

import com.robotnec.budget.app.activity.AddAccountActivity
import com.robotnec.budget.app.activity.AddCategoryActivity
import com.robotnec.budget.app.activity.AddTransactionActivity
import com.robotnec.budget.app.activity.CategoryOverviewActivity
import com.robotnec.budget.core.domain.Account
import com.robotnec.budget.core.domain.Category
import com.robotnec.budget.core.navigator.NavigationBundle
import com.robotnec.budget.core.navigator.Navigator
import com.robotnec.budget.app.util.Keys

/**
 * @author zak zak@swingpulse.com>
 */
class AndroidNavigator : Navigator {

    override fun openAddTransactionScreen(navigationBundle: NavigationBundle<*>) {
        val androidNavigationBundle = navigationBundle as AndroidNavigationBundle
        val activity = androidNavigationBundle.navigationContext
        val intent = Intent(activity, AddTransactionActivity::class.java)
        activity.startActivity(
                intent,
                ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())
    }

    override fun openAddCategoryScreen(navigationBundle: NavigationBundle<*>) {
        val androidNavigationBundle = navigationBundle as AndroidNavigationBundle
        val activity = androidNavigationBundle.navigationContext

        val category = androidNavigationBundle.serializableExtra as? Category

        val intent = Intent(activity, AddCategoryActivity::class.java)
        intent.putExtra(Keys.CATEGORY, category)

        activity.startActivity(
                intent,
                ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())
    }

    override fun openAddAccountScreen(navigationBundle: NavigationBundle<*>) {
        val androidNavigationBundle = navigationBundle as AndroidNavigationBundle
        val activity = androidNavigationBundle.navigationContext

        val account = androidNavigationBundle.serializableExtra as? Account

        val intent = Intent(activity, AddAccountActivity::class.java)
        intent.putExtra(Keys.ACCOUNT, account)

        activity.startActivity(
                intent,
                ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())
    }

    override fun openCategory(activity: Activity, category: Category) {
        val intent = Intent(activity, CategoryOverviewActivity::class.java)
        intent.putExtra(Keys.CATEGORY, category)

        activity.startActivity(
                intent,
                ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())
    }
}
