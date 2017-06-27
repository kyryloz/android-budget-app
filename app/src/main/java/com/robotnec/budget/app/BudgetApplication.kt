package com.robotnec.budget.app

import android.support.multidex.MultiDexApplication
import android.support.v7.app.AppCompatDelegate
import com.jakewharton.threetenabp.AndroidThreeTen
import com.robotnec.budget.app.navigator.AndroidNavigator
import com.robotnec.budget.core.di.ApplicationGraph

/**
 * @author zak zak@swingpulse.com>
 */
class BudgetApplication : MultiDexApplication() {

    lateinit var applicationGraph: ApplicationGraph

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        applicationGraph = ApplicationGraph(
                AndroidNavigator(),
                this)
    }
}
