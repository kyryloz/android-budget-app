package com.robotnec.budget.core.service

import com.robotnec.budget.core.domain.Currency
import com.robotnec.budget.core.domain.MoneyAmount

/**
 * @author zak zak@swingpulse.com>
 */
interface CurrencyExchangeService {
    fun exchange(from: MoneyAmount, to: Currency): MoneyAmount
}
