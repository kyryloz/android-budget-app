package com.robotnec.budget.core.domain;

import com.robotnec.budget.core.service.CurrencyExchangeService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author zak <zak@swingpulse.com>
 */
public class MoneyAmount {

    private final static NumberFormat numberFormat;

    static {
        numberFormat = DecimalFormat.getInstance();
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setRoundingMode(RoundingMode.UP);
    }

    private final BigDecimal amount;
    private final Currency currency;

    public static MoneyAmount fromDbString(String dbString) {
        String[] split = dbString.split(" ");
        return new MoneyAmount(new BigDecimal(split[0]), Currency.fromCode(split[1]));
    }

    public MoneyAmount(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Operations operations() {
        return new Operations() {
            @Override
            public BigDecimal divide(BigDecimal divider, RoundingMode roundingMode) {
                return amount.divide(divider, 4, roundingMode);
            }

            @Override
            public BigDecimal multiply(BigDecimal multiplier) {
                return amount.multiply(multiplier);
            }
        };
    }

    public String toDisplayableString() {
        return String.format("%s %s", currency.getSymbol(), numberFormat.format(amount));
    }

    public MoneyAmount add(CurrencyExchangeService exchangeService, MoneyAmount other) {
        MoneyAmount result = exchangeService.exchange(other, currency);
        return new MoneyAmount(amount.add(result.amount), currency);
    }

    public String toDbString() {
        return String.format("%s %s", amount.toPlainString(), currency.getCode());
    }

    public Currency getCurrency() {
        return currency;
    }

    public interface Operations {
        BigDecimal divide(BigDecimal divider, RoundingMode roundingMode);

        BigDecimal multiply(BigDecimal multiplier);
    }
}
