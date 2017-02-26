package com.robotnec.budget.core.mvp.presenter;

import com.robotnec.budget.core.calculator.CalculatorModel;
import com.robotnec.budget.core.calculator.CalculatorModelImpl;
import com.robotnec.budget.core.calculator.Op;
import com.robotnec.budget.core.di.ApplicationComponent;
import com.robotnec.budget.core.exception.InvalidExpressionException;
import com.robotnec.budget.core.mvp.view.CalculatorView;

/**
 * @author zak <zak@swingpulse.com>
 */
public class CalculatorPresenter extends Presenter<CalculatorView> {

    private final CalculatorModel calculatorModel;
    private boolean done;

    public CalculatorPresenter(CalculatorView view) {
        super(view);
        calculatorModel = new CalculatorModelImpl();
        done = false;
    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    @Override
    public void onViewResume() {
        calculate();
    }

    public void digit(int digit) {
        display(calculatorModel.digit(digit));
    }

    public void dot() {
        display(calculatorModel.dot());
    }

    public void calculate() {
        try {
            String displayText = calculatorModel.calculate();
            double number = Double.parseDouble(displayText);
            if (Double.isInfinite(number) || Double.isNaN(number)) {
                display(displayText);
                view.displayError();
            } else {
                if (done) {
                    view.done(Double.parseDouble(displayText));
                } else {
                    display(displayText);
                    done = true;
                    view.displayDone();
                }
            }
        } catch (InvalidExpressionException e) {
            view.displayError();
        }
    }

    public void divide() {
        display(calculatorModel.operation(Op.DIVIDE));
    }

    public void multiply() {
        display(calculatorModel.operation(Op.MULTIPLY));
    }

    public void minus() {
        display(calculatorModel.operation(Op.MINUS));
    }

    public void plus() {
        display(calculatorModel.operation(Op.PLUS));
    }

    public void back() {
        display(calculatorModel.back());
    }

    public void clear() {
        display(calculatorModel.clear());
    }

    private void display(String displayText) {
        view.display(displayText);
        view.clearError();
        view.clearDone();
        done = false;
    }
}
