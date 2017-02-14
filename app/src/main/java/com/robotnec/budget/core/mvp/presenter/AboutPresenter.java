package com.robotnec.budget.core.mvp.presenter;

import com.robotnec.budget.core.di.ApplicationComponent;
import com.robotnec.budget.core.mvp.view.AboutView;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AboutPresenter extends Presenter<AboutView> {

    public AboutPresenter(AboutView view) {
        super(view);
    }

    @Override
    public void onViewResume() {

    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }
}
