package com.robotnec.budget.app.fragments

import androidx.fragment.app.Fragment
import com.robotnec.budget.R
import com.robotnec.budget.core.mvp.presenter.AboutPresenter
import com.robotnec.budget.core.mvp.view.AboutView

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
}
