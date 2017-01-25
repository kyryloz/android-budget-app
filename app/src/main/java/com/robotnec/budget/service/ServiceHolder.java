package com.robotnec.budget.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * @author zak <zak@swingpulse.com>
 */
public class ServiceHolder<S, B extends LocalBinder<S>> {

    private final Context context;
    S service;
    private boolean bound;

    public ServiceHolder(Context context) {
        this.context = context;
        bound = false;
    }

    public void bindService(Class<S> serviceClass) {
        Intent intent = new Intent(context, serviceClass);
        context.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    public void stop() {
        if (bound) {
            context.unbindService(mConnection);
            bound = false;
        }
    }

    public S getService() {
        if (bound) {
            return service;
        } else {
            throw new IllegalStateException("Service is not bound");
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {

        @SuppressWarnings("unchecked")
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            B localBinder = (B) service;
            ServiceHolder.this.service = localBinder.getService();
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bound = false;
        }
    };
}
