package com.robotnec.budget.app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.LayoutInflater
import android.view.ViewGroup
import com.robotnec.budget.R
import com.robotnec.budget.app.BudgetApplication
import com.robotnec.budget.core.mvp.presenter.Presenter
import com.robotnec.budget.core.mvp.view.View
import kotlinx.android.synthetic.main.activity_main.drawer

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

    protected fun initToolbarToggle(toolbar: Toolbar) {
        activity?.let {
            val drawer = it.drawer
            val toggle = ActionBarDrawerToggle(
                    it, drawer, toolbar,
                    R.string.navigation_drawer_open,
                    R.string.navigation_drawer_close)
            drawer.addDrawerListener(toggle)
            toggle.syncState()
        }
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
