package ua.com.zak.budgetswing.model.domen;

/**
 * @author zak <zak@swingpulse.com>
 */
public class Account {

    private long mId;
    private String mName;
    private long mAmount;
    private String mCurrencyCode;

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public long getAmount() {
        return mAmount;
    }

    public void setAmount(long amount) {
        mAmount = amount;
    }

    public String getCurrencyCode() {
        return mCurrencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        mCurrencyCode = currencyCode;
    }
}
