package ua.com.zak.budgetswing.core.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ua.com.zak.budgetswing.core.navigator.Navigator;

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
