package com.robotnec.budget.refactored

import androidx.appcompat.app.AppCompatDelegate
import com.jakewharton.threetenabp.AndroidThreeTen
import com.robotnec.budget.refactored.di.DaggerApplicationComponent
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
