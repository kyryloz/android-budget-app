package com.robotnec.budget.core.domain.operation

import com.robotnec.budget.core.domain.Account
import com.robotnec.budget.core.domain.Category
import com.robotnec.budget.core.domain.Identifiable
import com.robotnec.budget.core.domain.MoneyAmount

import org.threeten.bp.LocalDateTime


/**
 * @author zak zak@swingpulse.com>
 */
data class Transaction(override var id: Long = -1,
                       var amount: MoneyAmount,
                       var date: LocalDateTime,
                       var account: Account,
                       var category: Category) : Identifiable
