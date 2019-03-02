package com.robotnec.budget.refactored.di

import com.robotnec.budget.refactored.di.helpers.ViewModelBuilderModule
import com.robotnec.budget.refactored.di.module.*
import com.robotnec.budget.refactored.screens.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module(
        includes = [
            ViewModelBuilderModule::class,
            ViewModelModule::class,
            DaoModule::class,
            NavigationModule::class,
            AndroidModule::class,
            ServiceModule::class
        ]
)
abstract class AndroidComponentsInjectionModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeFragmentInjector(): HomeFragment
}