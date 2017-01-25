package com.robotnec.budget.service;

import android.os.Binder;

/**
 * @author zak <zak@swingpulse.com>
 */
public class LocalBinder<S> extends Binder {

    private final S mServiceInstance;

    public LocalBinder(S serviceInstance) {
        mServiceInstance = serviceInstance;
    }

    S getService() {
        return mServiceInstance;
    }
}
