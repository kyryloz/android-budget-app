package com.robotnec.budget.core.service

import com.robotnec.budget.core.domain.Currency
import com.robotnec.budget.core.domain.MoneyAmount
import com.robotnec.budget.core.service.impl.SimpleCurrencyExchangeService

import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * @author zak zak@swingpulse.com>
 */
class CurrencyExchangeServiceTest {

    private lateinit var currencyExchangeService: CurrencyExchangeService

    @Before
    @Throws(Exception::class)
    fun before() {
        currencyExchangeService = SimpleCurrencyExchangeService()
    }

    @Test
    @Throws(Exception::class)
    fun exchange() {
        val from = MoneyAmount.of(1.0, Currency.USD)
        val actual = currencyExchangeService.exchange(from, Currency.UAH)
        Assert.assertEquals(MoneyAmount.of(27.0, Currency.UAH), actual)
    }

}