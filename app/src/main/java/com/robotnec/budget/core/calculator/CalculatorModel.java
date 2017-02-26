package com.robotnec.budget.core.calculator;

import com.robotnec.budget.core.exception.InvalidExpressionException;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface CalculatorModel {
    String digit(int digit);

    String dot();

    String calculate() throws InvalidExpressionException;

    String operation(Op op);

    String back();

    String clear();
}
