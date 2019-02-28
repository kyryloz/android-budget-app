package com.robotnec.budget.app

import android.support.v7.app.AppCompatDelegate
import com.jakewharton.threetenabp.AndroidThreeTen
import com.robotnec.budget.core.di.DaggerApplicationComponent
import dagger.android.support.DaggerApplication

/**
 * @author zak zak@swingpulse.com>
 */
class BudgetApplication : DaggerApplication() {

    public override fun applicationInjector() = DaggerApplicationComponent.builder()
            .application(this)
            .build()

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }
}
