package com.robotnec.budget.core.navigator

import java.io.Serializable

/**
 * @author zak zak@swingpulse.com>
 */
interface NavigationBundle<out T> {
    val navigationContext: T
    var serializableExtra: Serializable?
}
