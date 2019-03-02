package com.robotnec.budget.refactored.di.module

import androidx.lifecycle.ViewModel
import com.robotnec.budget.refactored.di.helpers.ViewModelKey
import com.robotnec.budget.refactored.screens.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @[Binds IntoMap ViewModelKey(HomeViewModel::class)]
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel
}
