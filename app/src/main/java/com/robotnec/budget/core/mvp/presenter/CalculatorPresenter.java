package com.robotnec.budget.core.mvp.presenter;

import com.robotnec.budget.core.calculator.CalculatorModel;
import com.robotnec.budget.core.calculator.CalculatorModelImpl;
import com.robotnec.budget.core.di.ApplicationComponent;
import com.robotnec.budget.core.mvp.view.CalculatorView;

/**
 * @author zak <zak@swingpulse.com>
 */
public class CalculatorPresenter extends Presenter<CalculatorView> {

    private CalculatorModel calculatorModel;

    public CalculatorPresenter(CalculatorView view) {
        super(view);
        calculatorModel = new CalculatorModelImpl();
    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    @Override
    public void onViewResume() {
        display(calculatorModel.calculate());
    }

    public void digit(int digit) {
        display(calculatorModel.digit(digit));
    }

    public void dot() {
        display(calculatorModel.dot());
    }

    public void calculate() {
        display(calculatorModel.calculate());
    }

    public void divide() {
        display(calculatorModel.operation(Operation.DIVIDE));
    }

    public void multiply() {
        display(calculatorModel.operation(Operation.MULTIPLY));
    }

    public void minus() {
        display(calculatorModel.operation(Operation.MINUS));
    }

    public void plus() {
        display(calculatorModel.operation(Operation.PLUS));
    }

    public void back() {
        display(calculatorModel.back());
    }

    private void display(String displayText) {
        view.display(displayText);
    }
}
