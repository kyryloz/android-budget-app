package com.robotnec.budget.core.calculator;

/**
 * @author zak <zak@swingpulse.com>
 */
public class CalculatorModelImpl implements CalculatorModel {

    @Override
    public String digit(int digit) {
        return String.valueOf(digit);
    }

    @Override
    public String dot() {
        return ".";
    }

    @Override
    public String calculate() {
        return "90,000";
    }

    @Override
    public String operation(Operation operation) {
        return "+";
    }

    @Override
    public String back() {
        return "0";
    }
}
