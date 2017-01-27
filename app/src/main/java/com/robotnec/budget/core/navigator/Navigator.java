package com.robotnec.budget.core.navigator;

import android.app.Activity;

import com.robotnec.budget.core.domain.Category;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface Navigator {
    void openAddTransactionScreen(NavigationBundle navigationBundle);

    void openAddCategoryScreen(NavigationBundle navigationBundle);

    void openAddAccountScreen(NavigationBundle navigationBundle);

    void openCategory(Activity activity, Category category);
}
