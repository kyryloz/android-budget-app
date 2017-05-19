package com.robotnec.budget.app.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import com.robotnec.budget.app.BudgetApplication
import com.robotnec.budget.core.mvp.presenter.Presenter
import com.robotnec.budget.core.mvp.view.View

/**
 * @author zak zak@swingpulse.com>
 */
abstract class BasePresenterActivity<P : Presenter<*>> : AppCompatActivity(), View {

    protected lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        ButterKnife.bind(this)
        presenter = createPresenter()
        val applicationGraph = (application as BudgetApplication)
                .applicationGraph
        presenter.injectComponent(applicationGraph.applicationComponent)
        presenter.onViewResume()
    }

    protected abstract fun createPresenter(): P

    protected abstract val layoutId: Int
}
