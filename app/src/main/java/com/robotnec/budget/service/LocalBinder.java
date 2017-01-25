package com.robotnec.budget.service;

import android.os.Binder;

/**
 * @author zak <zak@swingpulse.com>
 */
public class LocalBinder<S> extends Binder {

    private final S serviceInstance;

    public LocalBinder(S serviceInstance) {
        this.serviceInstance = serviceInstance;
    }

    S getService() {
        return serviceInstance;
    }
}
