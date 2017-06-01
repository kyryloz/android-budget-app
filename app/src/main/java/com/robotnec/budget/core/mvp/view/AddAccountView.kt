package com.robotnec.budget.core.mvp.view

import com.robotnec.budget.core.domain.Account

/**
 * @author zak zak@swingpulse.com>
 */
interface AddAccountView : View {
    fun initEditMode(account: Account)

    fun closeView()
}
