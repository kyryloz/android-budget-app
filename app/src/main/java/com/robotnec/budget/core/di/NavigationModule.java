package com.robotnec.budget.core.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.robotnec.budget.core.navigator.Navigator;

/**
 * @author zak <zak@swingpulse.com>
 */
@Module
public class NavigationModule {

    private Navigator mNavigator;

    public NavigationModule(Navigator navigator) {
        mNavigator = navigator;
    }

    @Singleton
    @Provides
    public Navigator provideInternalNavigator() {
        return mNavigator;
    }
}
