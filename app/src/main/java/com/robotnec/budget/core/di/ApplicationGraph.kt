package com.robotnec.budget.core.di

import android.content.Context
import com.robotnec.budget.core.di.module.AndroidModule
import com.robotnec.budget.core.di.module.DaoModule
import com.robotnec.budget.core.di.module.NavigationModule
import com.robotnec.budget.core.navigator.Navigator
import com.robotnec.budget.core.persistence.BudgetDatabase

/**
 * @author zak zak@swingpulse.com>
 */
class ApplicationGraph(navigator: Navigator,
                       context: Context) {

    val applicationComponent: ApplicationComponent = DaggerApplicationComponent.builder()
            .daoModule(DaoModule(BudgetDatabase(context)))
            .navigationModule(NavigationModule(navigator))
            .androidModule(AndroidModule(context))
            .build()
}
