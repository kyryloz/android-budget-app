package com.robotnec.budget.core.calculator.eval;

import com.robotnec.budget.core.exception.InvalidExpressionException;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface Evaluator {
    String eval(String expression) throws InvalidExpressionException;
}
