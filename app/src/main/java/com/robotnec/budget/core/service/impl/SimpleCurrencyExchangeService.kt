package com.robotnec.budget.core.service.impl

import com.robotnec.budget.core.service.CurrencyExchangeService
import org.joda.money.CurrencyUnit
import org.joda.money.Money
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * @author zak zak@swingpulse.com>
 */
class SimpleCurrencyExchangeService : CurrencyExchangeService {

    override fun exchange(from: Money, to: CurrencyUnit): Money {
        val fromCurrency = from.currencyUnit
        if (fromCurrency == to) {
            return from
        }
        // TODO conversion rates
        val conversionRate = BigDecimal.TEN
        return from.convertedTo(to, conversionRate, RoundingMode.HALF_UP)
    }
}
