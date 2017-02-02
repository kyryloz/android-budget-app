package com.robotnec.budget.core.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.robotnec.budget.core.navigator.Navigator;

/**
 * @author zak <zak@swingpulse.com>
 */
@Module
public class NavigationModule {

    private Navigator navigator;

    public NavigationModule(Navigator navigator) {
        this.navigator = navigator;
    }

    @Singleton
    @Provides
    public Navigator provideInternalNavigator() {
        return navigator;
    }
}
