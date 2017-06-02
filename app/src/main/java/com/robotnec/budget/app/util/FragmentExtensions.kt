package com.robotnec.budget.app.util

import android.support.v4.app.Fragment
import org.jetbrains.anko.bundleOf

/**
 * Pass arguments to a Fragment without the hassle of
 * creating a static newInstance() method for every Fragment.
 *
 * Declared outside any class to have full access in any
 * part of your package.
 *
 * Usage: newFragment<MyFragment>("foo" to true, "bar" to 0)
 *
 * @return Returns an instance of Fragment as the specified generic type with the params applied as arguments
 */
inline fun <reified T : Fragment> newFragment(vararg params: Pair<String, Any>)
        = T::class.java.newInstance().apply {
    arguments = bundleOf(*params)
}!!