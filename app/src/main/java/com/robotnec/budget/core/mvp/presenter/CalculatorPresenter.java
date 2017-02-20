package com.robotnec.budget.core.mvp.presenter;

import com.robotnec.budget.core.di.ApplicationComponent;
import com.robotnec.budget.core.mvp.view.CalculatorView;

/**
 * @author zak <zak@swingpulse.com>
 */
public class CalculatorPresenter extends Presenter<CalculatorView> {

    private String current;

    public CalculatorPresenter(CalculatorView view) {
        super(view);
        current = "0";
    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    @Override
    public void onViewResume() {
        display();
    }

    public void digit(int digit) {
        if (current.equals("0")) {
            current = String.valueOf(digit);
        } else {
            current = current + digit;
        }
        display();
    }

    public void dot() {

    }

    public void calculate() {

    }

    public void divide() {

    }

    public void multiply() {

    }

    public void minus() {

    }

    public void plus() {

    }

    public void back() {
        current = current.substring(0, current.length() - 1);
        display();
    }

    private void display() {
        view.display(current);
    }
}
