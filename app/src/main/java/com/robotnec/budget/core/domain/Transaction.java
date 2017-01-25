package com.robotnec.budget.core.domain;

/**
 * @author zak <zak@swingpulse.com>
 */
public class Transaction {

    private final Account account;
    private final Category category;
    private final long amount;
    private final long date;
    private final String currency;

    private Transaction(Account account, Category category, long amount, String currency, long date) {
        this.account = account;
        this.category = category;
        this.amount = amount;
        this.currency = currency;
        this.date = date;
    }

    public Account getAccount() {
        return account;
    }

    public Category getCategory() {
        return category;
    }

    public long getAmount() {
        return amount;
    }

    public long getDate() {
        return date;
    }

    public String getCurrency() {
        return currency;
    }

    public static class Builder {
        private Account mAccount;
        private Category mCategory;
        private long mAmount;
        private String mCurrency;
        private long mDate;

        public Builder setAccount(Account account) {
            mAccount = account;
            return this;
        }

        public Builder setCategory(Category category) {
            mCategory = category;
            return this;
        }

        public Builder setAmount(long amount) {
            mAmount = amount;
            return this;
        }

        public Builder setCurrency(String currency) {
            mCurrency = currency;
            return this;
        }

        public Builder setDate(long date) {
            mDate = date;
            return this;
        }

        public Transaction build() {
            return new Transaction(mAccount, mCategory, mAmount, mCurrency, mDate);
        }
    }
}
