package com.robotnec.budget.app.activity

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.robotnec.budget.R
import com.robotnec.budget.core.mvp.presenter.MainPresenter
import com.robotnec.budget.core.mvp.view.MainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BasePresenterActivity<MainPresenter>(), MainView {

    override val layoutId = R.layout.activity_main

    override fun createPresenter(): MainPresenter {
        return MainPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navController = navigationHostFragment.findNavController()

        bottomNavigationView.setupWithNavController(navController)
    }
}
