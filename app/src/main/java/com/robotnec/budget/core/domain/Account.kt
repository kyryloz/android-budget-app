package com.robotnec.budget.core.domain

import java.io.Serializable

/**
 * @author zak zak@swingpulse.com>
 */
data class Account(override var id: Long,
                   override var name: String,
                   var amount: MoneyAmount) : Serializable, Entity
