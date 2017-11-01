package com.robotnec.budget.app.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.robotnec.budget.R
import com.robotnec.budget.core.mvp.presenter.AboutPresenter
import com.robotnec.budget.core.mvp.view.AboutView
import kotlinx.android.synthetic.main.fragment_about.toolbar

/**
 * @author zak zak@swingpulse.com>
 */
class AboutFragment : BasePresenterFragment<AboutPresenter>(), AboutView {

    companion object {
        fun newInstance(): Fragment {
            return AboutFragment()
        }
    }

    override val layoutId: Int
        get() = R.layout.fragment_about

    override fun createPresenter(): AboutPresenter {
        return AboutPresenter(this)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbarToggle(toolbar)
    }
}
