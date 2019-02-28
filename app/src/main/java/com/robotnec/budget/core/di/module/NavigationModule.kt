package com.robotnec.budget.core.di.module

import com.robotnec.budget.app.navigator.AndroidNavigator
import com.robotnec.budget.core.navigator.Navigator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author zak zak@swingpulse.com>
 */
@Module
class NavigationModule {

    @Singleton
    @Provides
    fun provideInternalNavigator(): Navigator {
        return AndroidNavigator()
    }
}
