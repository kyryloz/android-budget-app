package com.robotnec.budget.core.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Objects;

/**
 * @author zak <zak@swingpulse.com>
 */
public class MoneyAmount implements Serializable {

    private final static NumberFormat numberFormat;

    static {
        numberFormat = DecimalFormat.getInstance();
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setRoundingMode(RoundingMode.UP);
    }

    private final BigDecimal amount;
    private final Currency currency;

    public static MoneyAmount of(double sum, Currency currency) {
        return new MoneyAmount(new BigDecimal(sum), Objects.requireNonNull(currency));
    }

    public static MoneyAmount of(MoneyAmount sum, Currency currency) {
        return new MoneyAmount(sum.amount, Objects.requireNonNull(currency));
    }

    public static MoneyAmount fromDbString(String dbString) {
        String[] split = dbString.split(" ");
        return new MoneyAmount(new BigDecimal(split[0]), Currency.fromCode(split[1]));
    }

    private MoneyAmount(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public MoneyAmount add(MoneyAmount other) {
        checkCurrency(other);
        return new MoneyAmount(amount.add(other.amount), currency);
    }

    public MoneyAmount subtract(MoneyAmount other) {
        checkCurrency(other);
        return new MoneyAmount(amount.subtract(other.amount), currency);
    }

    public MoneyAmount multiply(double multiplier) {
        return new MoneyAmount(amount.multiply(BigDecimal.valueOf(multiplier)), currency);
    }

    public MoneyAmount divide(double divider, RoundingMode roundingMode) {
        return new MoneyAmount(amount.divide(BigDecimal.valueOf(divider), roundingMode), currency);
    }

    public String toDbString() {
        return String.format("%s %s", amount.toPlainString(), currency.getCode());
    }

    public synchronized String toDisplayableString() {
        return String.format("%s %s", currency.getSymbol(), numberFormat.format(amount));
    }

    public Currency getCurrency() {
        return currency;
    }

    private void checkCurrency(MoneyAmount other) {
        if (!currency.equals(other.currency)) {
            throw new IllegalArgumentException("Currency must be the same");
        }
    }

    public boolean isNegative() {
        return amount.signum() < 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MoneyAmount that = (MoneyAmount) o;

        if (amount.compareTo(that.amount) != 0) return false;
        return currency == that.currency;
    }

    @Override
    public int hashCode() {
        int result = amount.hashCode();
        result = 31 * result + currency.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("MoneyAmount{ %s %s }", amount, currency);
    }
}
