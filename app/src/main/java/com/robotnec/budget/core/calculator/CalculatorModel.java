package com.robotnec.budget.core.calculator;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface CalculatorModel {
    String digit(int digit);

    String dot();

    String calculate();

    String operation(Operation operation);

    String back();
}
