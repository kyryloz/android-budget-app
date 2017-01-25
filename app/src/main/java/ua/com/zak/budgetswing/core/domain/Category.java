package ua.com.zak.budgetswing.core.domain;

import java.io.Serializable;

/**
 * @author zak <zak@swingpulse.com>
 */
public class Category implements Serializable, Entity {
    private long mId;
    private String mName;

    @Override
    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    @Override
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
