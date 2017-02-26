package com.robotnec.budget.core.calculator;

import com.robotnec.budget.core.calculator.eval.Evaluator;
import com.robotnec.budget.core.calculator.eval.JEvalEvaluator;
import com.robotnec.budget.core.exception.InvalidExpressionException;

/**
 * @author zak <zak@swingpulse.com>
 */
public class CalculatorModelImpl implements CalculatorModel {

    private final int INIT_STATE = 0;
    private final int DIGIT_STATE = 1;
    private final int OPERATION_STATE = 2;
    private final int ERROR_STATE = 3;

    private int state;
    private Input input;
    private Evaluator evaluator;

    public CalculatorModelImpl() {
        state = INIT_STATE;
        input = new Input();
        evaluator = new JEvalEvaluator();
    }

    @Override
    public String digit(int digit) {
        String digitStr = String.valueOf(digit);
        switch (state) {
            case ERROR_STATE:
            case INIT_STATE:
                state = DIGIT_STATE;
                input.replace(digitStr);
                break;
            case DIGIT_STATE:
                input.append(digitStr);
                break;
            case OPERATION_STATE:
                state = DIGIT_STATE;
                input.append(digitStr);
                break;
            default:
                throw new IllegalArgumentException();
        }
        return input.toDisplayText();
    }

    @Override
    public String dot() {
        switch (state) {
            case ERROR_STATE:
            case INIT_STATE:
                state = DIGIT_STATE;
                input.dot();
                break;
            case DIGIT_STATE:
                input.dot();
                break;
            case OPERATION_STATE:
                break;
            default:
                throw new IllegalArgumentException();
        }
        return input.toDisplayText();
    }

    @Override
    public double calculate() throws InvalidExpressionException {
        switch (state) {
            case OPERATION_STATE:
            case ERROR_STATE:
                throw new InvalidExpressionException();
            case INIT_STATE:
                return 0d;
            case DIGIT_STATE:
                try {
                    double result = evaluator.eval(input.toExpression());
                    state = INIT_STATE;
                    input.clear();
                    return result;
                } catch (InvalidExpressionException e) {
                    state = ERROR_STATE;
                    throw e;
                }
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public String operation(Op op) {
        switch (state) {
            case ERROR_STATE:
                state = INIT_STATE;
                break;
            case INIT_STATE:
                break;
            case DIGIT_STATE:
                state = OPERATION_STATE;
                input.append(op);
                break;
            case OPERATION_STATE:
                break;
            default:
                throw new IllegalArgumentException();
        }
        return input.toDisplayText();
    }

    @Override
    public String back() {
        switch (state) {
            case ERROR_STATE:
                state = INIT_STATE;
                break;
            case INIT_STATE:
                break;
            case DIGIT_STATE:
            case OPERATION_STATE:
                boolean deletedLastSymbol = input.delete();
                if (deletedLastSymbol) {
                    state = INIT_STATE;
                } else {
                    if (input.lastSymbolIsDigit()) {
                        state = DIGIT_STATE;
                    } else {
                        state = OPERATION_STATE;
                    }
                }
                break;
            default:
                throw new IllegalArgumentException();
        }
        return input.toDisplayText();
    }

    @Override
    public String clear() {
        input.clear();
        state = INIT_STATE;
        return input.toDisplayText();
    }
}
