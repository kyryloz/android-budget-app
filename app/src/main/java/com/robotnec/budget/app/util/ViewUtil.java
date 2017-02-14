package com.robotnec.budget.app.util;

import android.content.Context;
import android.content.res.TypedArray;

import com.robotnec.budget.R;

/**
 * @author zak <zak@swingpulse.com>
 */
public class ViewUtil {

    public static int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{R.attr.actionBarSize});
        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return toolbarHeight;
    }
}
