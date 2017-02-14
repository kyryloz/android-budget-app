package com.robotnec.budget.core.mvp.presenter;


import com.robotnec.budget.core.di.ApplicationComponent;
import com.robotnec.budget.core.mvp.view.View;

/**
 * @author zak <zak@swingpulse.com>
 */
public abstract class Presenter<V extends View> {

    final V view;

    public Presenter(V view) {
        this.view = view;
    }

    public abstract void injectComponent(ApplicationComponent applicationComponent);

    public abstract void onViewResume();
}
