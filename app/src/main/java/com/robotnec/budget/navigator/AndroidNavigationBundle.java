package com.robotnec.budget.navigator;

import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;

import com.robotnec.budget.core.navigator.NavigationBundle;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AndroidNavigationBundle implements NavigationBundle<AppCompatActivity> {

    private AppCompatActivity mActivity;
    private Serializable mSerializableExtra;

    public AndroidNavigationBundle(AppCompatActivity activity) {
        mActivity = activity;
    }

    @Override
    public AppCompatActivity getNavigationContext() {
        return mActivity;
    }

    @Override
    public Serializable getSerializableExtra() {
        return mSerializableExtra;
    }

    @Override
    public void setSerializableExtra(Serializable serializableExtra) {
        mSerializableExtra = serializableExtra;
    }
}
