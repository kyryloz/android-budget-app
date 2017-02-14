package com.robotnec.budget.app.util;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

/**
 * @author zak <zak@swingpulse.com>
 */
public class TextIconUtils {

    public static TextDrawable generate(String name) {
        return TextDrawable.builder().buildRound(name.substring(0, 1),
                ColorGenerator.MATERIAL.getColor(name));
    }
}
