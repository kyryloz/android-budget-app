package com.robotnec.budget.core.domain

import java.io.Serializable
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Objects

/**
 * @author zak zak@swingpulse.com>
 */
data class MoneyAmount constructor(private val amount: BigDecimal, val currency: Currency) : Serializable {

    fun add(other: MoneyAmount): MoneyAmount {
        checkCurrency(other)
        return MoneyAmount(amount.add(other.amount), currency)
    }

    fun subtract(other: MoneyAmount): MoneyAmount {
        checkCurrency(other)
        return MoneyAmount(amount.subtract(other.amount), currency)
    }

    fun multiply(multiplier: Double): MoneyAmount {
        return MoneyAmount(amount.multiply(BigDecimal.valueOf(multiplier)), currency)
    }

    fun divide(divider: Double, roundingMode: RoundingMode): MoneyAmount {
        return MoneyAmount(amount.divide(BigDecimal.valueOf(divider), roundingMode), currency)
    }

    fun toDbString(): String {
        return String.format("%s %s", amount.toPlainString(), currency.code)
    }

    @Synchronized fun toDisplayableString(): String {
        return String.format("%s %s", currency.symbol, numberFormat.format(amount))
    }

    private fun checkCurrency(other: MoneyAmount) {
        if (currency != other.currency) {
            throw IllegalArgumentException("Currency must be the same")
        }
    }

    val isNegative: Boolean
        get() = amount.signum() < 0

    companion object {

        private val numberFormat: NumberFormat = DecimalFormat.getInstance()

        init {
            numberFormat.minimumFractionDigits = 2
            numberFormat.maximumFractionDigits = 2
            numberFormat.roundingMode = RoundingMode.UP
        }

        fun of(sum: Double, currency: Currency): MoneyAmount {
            return MoneyAmount(BigDecimal(sum), Objects.requireNonNull(currency))
        }

        fun of(sum: MoneyAmount, currency: Currency): MoneyAmount {
            return MoneyAmount(sum.amount, Objects.requireNonNull(currency))
        }

        fun fromDbString(dbString: String): MoneyAmount {
            val split = dbString.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            return MoneyAmount(BigDecimal(split[0]), Currency.fromCode(split[1]))
        }
    }
}
