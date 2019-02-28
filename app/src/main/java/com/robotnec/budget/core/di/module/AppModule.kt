package com.robotnec.budget.core.di.module

import com.robotnec.budget.core.di.helpers.ViewModelBuilderModule
import dagger.Module

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
class AppModule