package com.robotnec.budget.core.calculator;

import android.text.TextUtils;

import com.robotnec.budget.core.calculator.eval.ArityEvaluator;
import com.robotnec.budget.core.calculator.eval.Evaluator;

/**
 * @author zak <zak@swingpulse.com>
 */
public class CalculatorModelImpl implements CalculatorModel {

    private static final String INIT_VALUE = "0";

    private final int INIT_STATE = 0;
    private final int DIGIT_STATE = 1;
    private final int OPERATION_STATE = 3;

    private int state;
    private String displayText;
    private Evaluator evaluator;

    public CalculatorModelImpl() {
        state = INIT_STATE;
        displayText = INIT_VALUE;
        evaluator = new ArityEvaluator();
    }

    @Override
    public String digit(int digit) {
        switch (state) {
            case INIT_STATE:
                state = DIGIT_STATE;
                displayText = String.valueOf(digit);
                break;
            case DIGIT_STATE:
                displayText = displayText + String.valueOf(digit);
                break;
            case OPERATION_STATE:
                state = DIGIT_STATE;
                displayText = displayText + String.valueOf(digit);
                break;
            default:
                throw new IllegalArgumentException();
        }
        return displayText;
    }

    @Override
    public String dot() {
        return ".";
    }

    @Override
    public double calculate() {
        double result = evaluator.eval(displayText);
        state = INIT_STATE;
        displayText = String.valueOf(result);
        return result;
    }

    @Override
    public String operation(Op op) {
        switch (state) {
            case INIT_STATE:
                break;
            case DIGIT_STATE:
                state = OPERATION_STATE;
                displayText = displayText + op.displayText();
                break;
            case OPERATION_STATE:
                break;
            default:
                throw new IllegalArgumentException();
        }
        return displayText;
    }

    @Override
    public String back() {
        switch (state) {
            case INIT_STATE:
                break;
            case DIGIT_STATE:
            case OPERATION_STATE:
                if (displayText.length() == 1) {
                    displayText = INIT_VALUE;
                    state = INIT_STATE;
                    break;
                } else {
                    displayText = displayText.substring(0, displayText.length() - 1);
                    String last = displayText.substring(
                            displayText.length() - 1,
                            displayText.length());
                    if (TextUtils.isDigitsOnly(last)) {
                        state = DIGIT_STATE;
                    } else {
                        state = OPERATION_STATE;
                    }
                }
                break;
            default:
                throw new IllegalArgumentException();
        }
        return displayText;
    }

    private class Command implements Comparable<Command> {
        private final double firstOperand;
        private final double secondOperand;
        private final Op op;

        private Command(double firstOperand, double secondOperand, Op op) {
            this.firstOperand = firstOperand;
            this.secondOperand = secondOperand;
            this.op = op;
        }

        double calculate() {
            return op.calculate(firstOperand, secondOperand);
        }

        @Override
        public int compareTo(Command o) {
            return op.compareTo(o.op);
        }
    }
}
