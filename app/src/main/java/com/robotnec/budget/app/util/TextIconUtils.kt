package com.robotnec.budget.app.util

import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator

/**
 * @author zak zak@swingpulse.com>
 */
object TextIconUtils {

    fun generate(name: String): TextDrawable {
        return TextDrawable.builder().buildRound(name.substring(0, 1), ColorGenerator.MATERIAL.getColor(name))
    }
}
