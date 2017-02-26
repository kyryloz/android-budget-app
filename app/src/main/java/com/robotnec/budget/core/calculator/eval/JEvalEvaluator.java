package com.robotnec.budget.core.calculator.eval;

import com.robotnec.budget.core.exception.InvalidExpressionException;

import net.sourceforge.jeval.EvaluationException;

/**
 * @author zak <zak@swingpulse.com>
 */
public class JEvalEvaluator implements Evaluator {

    private net.sourceforge.jeval.Evaluator evaluator = new net.sourceforge.jeval.Evaluator();

    @Override

    public String eval(String expression) throws InvalidExpressionException {
        try {
            return evaluator.evaluate(expression);
        } catch (EvaluationException e) {
            throw new InvalidExpressionException(e);
        }
    }
}
