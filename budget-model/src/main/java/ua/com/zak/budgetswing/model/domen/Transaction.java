package ua.com.zak.budgetswing.model.domen;

/**
 * @author zak <zak@swingpulse.com>
 */
public class Transaction {

    private final long mAccountId;
    private final long mCategoryId;
    private final long mAmount;
    private final long mDate;

    private Transaction(long accountId, long categoryId, long amount, long date) {
        mAccountId = accountId;
        mCategoryId = categoryId;
        mAmount = amount;
        mDate = date;
    }

    public long getAccountId() {
        return mAccountId;
    }

    public long getCategoryId() {
        return mCategoryId;
    }

    public long getAmount() {
        return mAmount;
    }

    public long getDate() {
        return mDate;
    }

    public static class Builder {
        private long mAccountId;
        private long mCategoryId;
        private long mAmount;
        private long mDate;

        public Builder setAccountId(long accountId) {
            mAccountId = accountId;
            return this;
        }

        public Builder setCategoryId(long categoryId) {
            mCategoryId = categoryId;
            return this;
        }

        public Builder setAmount(long amount) {
            mAmount = amount;
            return this;
        }

        public Builder setDate(long date) {
            mDate = date;
            return this;
        }

        public Transaction build() {
            return new Transaction(mAccountId, mCategoryId, mAmount, mDate);
        }
    }
}
