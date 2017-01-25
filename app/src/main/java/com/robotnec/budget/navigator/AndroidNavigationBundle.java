package com.robotnec.budget.navigator;

import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;

import com.robotnec.budget.core.navigator.NavigationBundle;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AndroidNavigationBundle implements NavigationBundle<AppCompatActivity> {

    private AppCompatActivity activity;
    private Serializable serializableExtra;

    public AndroidNavigationBundle(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public AppCompatActivity getNavigationContext() {
        return activity;
    }

    @Override
    public Serializable getSerializableExtra() {
        return serializableExtra;
    }

    @Override
    public void setSerializableExtra(Serializable serializableExtra) {
        this.serializableExtra = serializableExtra;
    }
}
