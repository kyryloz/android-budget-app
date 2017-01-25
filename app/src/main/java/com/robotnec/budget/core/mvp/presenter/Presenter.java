package com.robotnec.budget.core.mvp.presenter;


import com.robotnec.budget.core.di.ApplicationComponent;
import com.robotnec.budget.core.mvp.view.View;

/**
 * @author zak <zak@swingpulse.com>
 */
public abstract class Presenter<V extends View> {

    protected final V mView;

    public Presenter(V view) {
        mView = view;
    }

    public abstract void injectComponent(ApplicationComponent applicationComponent);

    public abstract void onViewReady();
}
