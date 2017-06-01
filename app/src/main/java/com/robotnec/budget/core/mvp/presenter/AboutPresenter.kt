package com.robotnec.budget.core.mvp.presenter

import com.robotnec.budget.core.di.ApplicationComponent
import com.robotnec.budget.core.mvp.view.AboutView

/**
 * @author zak zak@swingpulse.com>
 */
class AboutPresenter(view: AboutView) : Presenter<AboutView>(view) {

    override fun onViewResume() {

    }

    override fun injectComponent(applicationComponent: ApplicationComponent) {
        applicationComponent.inject(this)
    }
}
