package com.robotnec.budget.app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import com.robotnec.budget.refactored.BudgetApplication
import com.robotnec.budget.core.mvp.presenter.Presenter
import com.robotnec.budget.core.mvp.view.View

/**
 * @author zak zak@swingpulse.com>
 */
abstract class BasePresenterFragment<P : Presenter<*>> : Fragment(), View {

    protected lateinit var presenter: P

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): android.view.View? {
        val root = inflater.inflate(layoutId, container, false)
        presenter = createPresenter()
        val application = (activity
                ?.application as BudgetApplication)
        presenter.injectComponent(application.applicationInjector())
        return root
    }

    override fun onResume() {
        super.onResume()
        presenter.onViewResume()
    }

    protected abstract val layoutId: Int

    protected abstract fun createPresenter(): P
}
