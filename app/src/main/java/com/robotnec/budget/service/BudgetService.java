package com.robotnec.budget.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class BudgetService extends Service {

    private final IBinder localBinder;

    public BudgetService() {
        localBinder = new LocalBinder<>(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }
}
