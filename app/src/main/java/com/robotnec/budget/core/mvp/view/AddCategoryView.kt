package com.robotnec.budget.core.mvp.view

import com.robotnec.budget.core.domain.Category

/**
 * @author zak zak@swingpulse.com>
 */
interface AddCategoryView : View {
    fun initEditMode(category: Category)

    fun closeView()
}
