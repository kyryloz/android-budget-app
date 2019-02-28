package com.robotnec.budget.core.di.helpers

import androidx.lifecycle.ViewModelProvider
import com.robotnec.budget.core.di.helpers.DaggerViewModelFactory
import dagger.Binds
import dagger.Module

@Suppress("unused")
@Module
abstract class ViewModelBuilderModule {

    @Binds
    abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory
}