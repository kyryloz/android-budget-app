package ua.com.zak.budgetswing.core.domain;

/**
 * @author zak <zak@swingpulse.com>
 */
public class Transaction {

    private final Account mAccount;
    private final Category mCategory;
    private final long mAmount;
    private final long mDate;
    private final String mCurrency;

    private Transaction(Account account, Category category, long amount, String currency, long date) {
        mAccount = account;
        mCategory = category;
        mAmount = amount;
        mCurrency = currency;
        mDate = date;
    }

    public Account getAccount() {
        return mAccount;
    }

    public Category getCategory() {
        return mCategory;
    }

    public long getAmount() {
        return mAmount;
    }

    public long getDate() {
        return mDate;
    }

    public String getCurrency() {
        return mCurrency;
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
