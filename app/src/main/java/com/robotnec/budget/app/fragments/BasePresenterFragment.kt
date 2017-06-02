package com.robotnec.budget.app.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.ViewGroup
import com.robotnec.budget.R
import com.robotnec.budget.app.BudgetApplication
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
        val applicationGraph = (activity
                .application as BudgetApplication)
                .applicationGraph
        presenter.injectComponent(applicationGraph.applicationComponent)
        return root
    }

    override fun onResume() {
        super.onResume()
        presenter.onViewResume()
    }

    protected fun initToolbarToggle(toolbar: Toolbar) {
        val drawer = activity.findViewById(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                activity, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    protected fun initToolbarBack(toolbar: Toolbar) {
        val activity = activity as AppCompatActivity
        activity.setSupportActionBar(toolbar)

        val supportActionBar = activity.supportActionBar

        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true)
            supportActionBar.setDisplayShowHomeEnabled(true)
        }

        toolbar.setNavigationOnClickListener { activity.finish() }
    }

    protected abstract val layoutId: Int

    protected abstract fun createPresenter(): P
}