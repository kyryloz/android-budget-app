package com.robotnec.budget.core.di.module

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module
class AndroidModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideApplicationContext(): Context {
        return context
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }
}
