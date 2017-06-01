package com.robotnec.budget.core.service.impl

import com.robotnec.budget.core.domain.Currency
import com.robotnec.budget.core.domain.MoneyAmount
import com.robotnec.budget.core.service.CurrencyExchangeService
import java.math.RoundingMode

/**
 * @author zak zak@swingpulse.com>
 */
class SimpleCurrencyExchangeService : CurrencyExchangeService {

    override fun exchange(from: MoneyAmount, to: Currency): MoneyAmount {
        val fromCurrency = from.currency
        if (fromCurrency == to) {
            return from
        }
        if (fromCurrency == Currency.USD && to == Currency.UAH) {
            val result = from.multiply(exchangeRates[Currency.UAH]!!)
            return MoneyAmount.of(result, to)
        }
        if (fromCurrency == Currency.UAH && to == Currency.USD) {
            val result = from.divide(exchangeRates[Currency.UAH]!!, RoundingMode.CEILING)
            return MoneyAmount.of(result, to)
        }
        throw UnsupportedOperationException("Can't exchange " + from.currency + " to " + to)
    }

    companion object {
        private val exchangeRates = mapOf(Currency.UAH to 27.0)
    }
}
