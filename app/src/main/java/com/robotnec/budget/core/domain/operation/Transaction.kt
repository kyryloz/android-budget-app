package com.robotnec.budget.core.domain.operation

import com.robotnec.budget.core.domain.Account
import com.robotnec.budget.core.domain.Category
import com.robotnec.budget.core.domain.Identifiable
import org.joda.money.Money

import org.threeten.bp.LocalDateTime


/**
 * @author zak zak@swingpulse.com>
 */
data class Transaction(override var id: Long = -1,
                       var amount: Money,
                       var date: LocalDateTime,
                       var account: Account,
                       var category: Category) : Identifiable
