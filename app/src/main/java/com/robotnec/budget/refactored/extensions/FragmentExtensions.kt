package com.robotnec.budget.refactored.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlin.reflect.KClass

fun <T : ViewModel> Fragment.getViewModel(
        factory: ViewModelProvider.Factory,
        modelClass: KClass<T>,
        activity: FragmentActivity? = null
): T {
    return when {
        activity != null -> ViewModelProviders.of(activity, factory).get(modelClass.java)
        else -> ViewModelProviders.of(this, factory).get(modelClass.java)
    }
}