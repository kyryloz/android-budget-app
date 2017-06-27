package com.robotnec.budget.core.service

import org.joda.money.CurrencyUnit
import org.joda.money.Money

/**
 * @author zak zak@swingpulse.com>
 */
interface CurrencyExchangeService {
    fun exchange(from: Money, to: CurrencyUnit): Money
}
