package com.robotnec.budget.core.domain;

import android.content.Context;

import com.robotnec.budget.R;
import com.robotnec.budget.core.service.CurrencyExchangeService;

import java.math.BigDecimal;

/**
 * @author zak <zak@swingpulse.com>
 */
public class MoneyAmount {
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

    public String toDisplayableString(Context context) {
        return context.getString(R.string.accounts_amount_format,
                currency.getSymbol(),
                amount.toPlainString());
    }

    public MoneyAmount add(CurrencyExchangeService exchangeService, MoneyAmount other) {
        BigDecimal result = amount.add(exchangeService.exchange(currency, other.currency, other.amount));
        return new MoneyAmount(result, currency);
    }

    public String toDbString() {
        return String.format("%s %s", amount.toPlainString(), currency.getCode());
    }
}
