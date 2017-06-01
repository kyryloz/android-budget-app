package com.robotnec.budget.core.navigator

import android.app.Activity

import com.robotnec.budget.core.domain.Category

/**
 * @author zak zak@swingpulse.com>
 */
interface Navigator {
    fun openAddTransactionScreen(navigationBundle: NavigationBundle<*>)

    fun openAddCategoryScreen(navigationBundle: NavigationBundle<*>)

    fun openAddAccountScreen(navigationBundle: NavigationBundle<*>)

    fun openCategory(activity: Activity, category: Category)
}
