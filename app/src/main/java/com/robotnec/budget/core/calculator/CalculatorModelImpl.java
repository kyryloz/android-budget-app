package com.robotnec.budget.core.calculator;

import android.text.TextUtils;

/**
 * @author zak <zak@swingpulse.com>
 */
public class CalculatorModelImpl implements CalculatorModel {

    private final int INIT_STATE = 0;
    private final int DIGIT_STATE = 1;
    private final int OPERATION_STATE = 2;

    private int state;

    private String displayText;

    public CalculatorModelImpl() {
        state = INIT_STATE;
        displayText = "0";
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
    public String calculate() {
        state = INIT_STATE;
        displayText = "0";
        return displayText;
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
                    displayText = "0";
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
}
