package com.robotnec.budget.core.mvp.view

import com.robotnec.budget.core.domain.Account

/**
 * @author zak zak@swingpulse.com>
 */
interface AccountsView : View {
    fun displayAccounts(accounts: List<Account>)
}
