package com.robotnec.budget.core.mvp.presenter

import com.robotnec.budget.refactored.di.ApplicationComponent
import com.robotnec.budget.core.mvp.view.MainView
import com.robotnec.budget.core.navigator.Navigator
import javax.inject.Inject

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

}
