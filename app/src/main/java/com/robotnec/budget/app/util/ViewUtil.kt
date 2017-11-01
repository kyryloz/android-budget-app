package com.robotnec.budget.app.util

import android.content.Context
import com.robotnec.budget.R

/**
 * @author zak zak@swingpulse.com>
 */
object ViewUtil {

    fun getToolbarHeight(context: Context): Int {
        val styledAttributes = context.theme.obtainStyledAttributes(
                intArrayOf(R.attr.actionBarSize))
        val toolbarHeight = styledAttributes.getDimension(0, 0f).toInt()
        styledAttributes.recycle()

        return toolbarHeight
    }
}
