package ua.com.zak.budgetswing.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class BudgetService extends Service {

    private final IBinder mLocalBinder;

    public BudgetService() {
        mLocalBinder = new LocalBinder<>(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mLocalBinder;
    }
}
