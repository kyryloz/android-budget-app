package com.robotnec.budget.core.calculator;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.robotnec.budget.core.calculator.eval.Evaluator;
import com.robotnec.budget.core.calculator.eval.JEvalEvaluator;
import com.robotnec.budget.core.exception.InvalidExpressionException;

import java.text.DecimalFormat;
import java.util.Deque;
import java.util.List;

/**
 * @author zak <zak@swingpulse.com>
 */
public class CalculatorModelImpl implements CalculatorModel {

    private final int INIT_STATE = 0;
    private final int DIGIT_STATE = 1;
    private final int OPERATION_STATE = 2;
    private final int ERROR_STATE = 3;

    private final DecimalFormat displayFormat;

    private int state;
    private Input input;
    private Evaluator evaluator;

    public CalculatorModelImpl() {
        state = INIT_STATE;
        input = new Input();
        evaluator = new JEvalEvaluator();
        displayFormat = new DecimalFormat("#.##");
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
        String dot = ".";
        switch (state) {
            case ERROR_STATE:
            case INIT_STATE:
                input.replace(dot);
                state = DIGIT_STATE;
                break;
            case DIGIT_STATE:
                Input.Entry dotEntry = new Input.Entry(dot, dot, false);
                Input.Entry fakeOperationEntry = new Input.Entry("", "", true);
                List<Input.Entry> entries = Stream.of(input.getInputStack())
                        .map(entry -> {
                            if (entry.isOperation()) {
                                return fakeOperationEntry;
                            } else {
                                return entry;
                            }
                        })
                        .collect(Collectors.toList());
                int indexDotEntry = entries.lastIndexOf(dotEntry);
                int indexOperationEntry = entries.lastIndexOf(fakeOperationEntry);
                if (indexDotEntry <= indexOperationEntry) {
                    input.append(dot);
                }
                break;
            case OPERATION_STATE:
                input.append(dot);
                state = DIGIT_STATE;
                break;
            default:
                throw new IllegalArgumentException();
        }
        return input.toDisplayText();
    }

    @Override
    public String calculate() throws InvalidExpressionException {
        switch (state) {
            case OPERATION_STATE:
            case ERROR_STATE:
                throw new InvalidExpressionException();
            case INIT_STATE:
                break;
            case DIGIT_STATE:
                String resultStr;
                try {
                    resultStr = evaluator.eval(input.toExpression());
                } catch (InvalidExpressionException e) {
                    state = ERROR_STATE;
                    throw e;
                }
                double resultNumber = Double.parseDouble(resultStr);
                if (Double.isInfinite(resultNumber) || Double.isNaN(resultNumber)) {
                    state = ERROR_STATE;
                    return String.valueOf(resultNumber);
                } else {
                    resultStr = displayFormat.format(resultNumber);
                    input.clear();
                    for (char c : String.valueOf(resultStr).toCharArray()) {
                        input.append(String.valueOf(c));
                    }
                    state = DIGIT_STATE;
                }
                break;
            default:
                throw new IllegalArgumentException();
        }
        return input.toDisplayText();
    }

    @Override
    public String operation(Op op) {
        switch (state) {
            case ERROR_STATE:
                input.clear();
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
                input.clear();
                state = INIT_STATE;
                break;
            case INIT_STATE:
                break;
            case DIGIT_STATE:
            case OPERATION_STATE:
                input.delete();
                Deque<Input.Entry> inputStack = input.getInputStack();
                if (inputStack.isEmpty()) {
                    state = INIT_STATE;
                } else if (inputStack.peekLast().isOperation()) {
                    state = OPERATION_STATE;
                } else {
                    state = DIGIT_STATE;
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
