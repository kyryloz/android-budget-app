package com.robotnec.budget.core.calculator;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface CalculatorModel {
    String digit(int digit);

    String dot();

    double calculate();

    String operation(Op op);

    String back();
}
