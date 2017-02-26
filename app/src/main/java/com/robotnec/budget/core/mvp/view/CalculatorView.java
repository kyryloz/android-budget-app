package com.robotnec.budget.core.mvp.view;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface CalculatorView extends View {
    void display(String value);

    void displayError();

    void displayDone();

    void close();

    void clearError();

    void clearDone();

    void done(double value);
}
