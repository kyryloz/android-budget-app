package ua.com.zak.budgetswing.core.navigator;

import java.io.Serializable;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface NavigationBundle<T> {
    T getNavigationContext();

    Serializable getSerializableExtra();

    void setSerializableExtra(Serializable serializableExtra);
}
