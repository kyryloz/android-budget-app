package com.robotnec.budget.core.di.module

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import com.robotnec.budget.core.navigator.Navigator

/**
 * @author zak zak@swingpulse.com>
 */
@Module
class NavigationModule(private val navigator: Navigator) {

    @Singleton
    @Provides
    fun provideInternalNavigator(): Navigator {
        return navigator
    }
}
