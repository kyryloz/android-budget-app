package ua.com.zak.budgetswing.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * @author zak <zak@swingpulse.com>
 */
public class ServiceHolder<S, B extends LocalBinder<S>> {

    private final Context mContext;
    S mService;
    private boolean mBound;

    public ServiceHolder(Context context) {
        mContext = context;
        mBound = false;
    }

    public void bindService(Class<S> serviceClass) {
        Intent intent = new Intent(mContext, serviceClass);
        mContext.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    public void stop() {
        if (mBound) {
            mContext.unbindService(mConnection);
            mBound = false;
        }
    }

    public S getService() {
        if (mBound) {
            return mService;
        } else {
            throw new IllegalStateException("Service is not bound");
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {

        @SuppressWarnings("unchecked")
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            B localBinder = (B) service;
            mService = localBinder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };
}
