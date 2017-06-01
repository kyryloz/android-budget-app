package com.robotnec.budget.app.navigator

import android.support.v7.app.AppCompatActivity
import com.robotnec.budget.core.navigator.NavigationBundle
import java.io.Serializable

/**
 * @author zak zak@swingpulse.com>
 */
@Deprecated("TODO rewrite to command-visitor")
class AndroidNavigationBundle(override val navigationContext: AppCompatActivity,
                              override var serializableExtra: Serializable?) : NavigationBundle<AppCompatActivity>
