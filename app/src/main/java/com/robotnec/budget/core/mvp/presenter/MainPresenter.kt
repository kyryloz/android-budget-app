package com.robotnec.budget.core.mvp.presenter

import javax.inject.Inject

import com.robotnec.budget.core.di.ApplicationComponent
import com.robotnec.budget.core.mvp.view.MainView
import com.robotnec.budget.core.navigator.NavigationBundle
import com.robotnec.budget.core.navigator.Navigator

/**
 * @author zak zak@swingpulse.com>
 */
class MainPresenter(view: MainView) : Presenter<MainView>(view) {

    @Inject
    internal lateinit var navigator: Navigator

    override fun onViewResume() {

    }

    override fun injectComponent(applicationComponent: ApplicationComponent) {
        applicationComponent.inject(this)
    }

    fun openMakeTransactionScreen(navigationBundle: NavigationBundle<*>) {
        navigator.openAddTransactionScreen(navigationBundle)
    }
}
