package com.robotnec.budget.core.mvp.presenter


import com.robotnec.budget.core.di.ApplicationComponent
import com.robotnec.budget.core.mvp.view.View

/**
 * @author zak zak@swingpulse.com>
 */
abstract class Presenter<out V : View>(protected val view: V) {

    abstract fun injectComponent(applicationComponent: ApplicationComponent)

    abstract fun onViewResume()
}
