package com.robotnec.budget.core.navigator

import android.app.Activity
import androidx.navigation.NavController

import com.robotnec.budget.core.domain.Category

/**
 * @author zak zak@swingpulse.com>
 */
interface Navigator {
    fun openAddTransactionScreen(navController: NavController)

    fun openAddCategoryScreen(navigationBundle: NavigationBundle<*>)

    fun openAddAccountScreen(navigationBundle: NavigationBundle<*>)

    fun openCategory(activity: Activity, category: Category)
}
