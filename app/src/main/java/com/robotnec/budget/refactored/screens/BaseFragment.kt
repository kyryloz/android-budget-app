package com.robotnec.budget.refactored.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.robotnec.budget.refactored.di.helpers.Injectable
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment : Fragment() {
    abstract val layoutId: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (javaClass.isAnnotationPresent(Injectable::class.java)) {
            AndroidSupportInjection.inject(this)
        }

        return inflater.inflate(layoutId, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView(savedInstanceState)
    }

    abstract fun initView(savedInstanceState: Bundle?)
}
