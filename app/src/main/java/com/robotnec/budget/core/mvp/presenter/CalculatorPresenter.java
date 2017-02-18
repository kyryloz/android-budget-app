package com.robotnec.budget.core.mvp.presenter;

import com.robotnec.budget.core.di.ApplicationComponent;
import com.robotnec.budget.core.mvp.view.CalculatorView;

/**
 * @author zak <zak@swingpulse.com>
 */
public class CalculatorPresenter extends Presenter<CalculatorView> {

    public CalculatorPresenter(CalculatorView view) {
        super(view);
    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    @Override
    public void onViewResume() {

    }
}
