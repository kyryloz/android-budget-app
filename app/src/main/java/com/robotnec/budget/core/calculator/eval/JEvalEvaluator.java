package com.robotnec.budget.core.calculator.eval;

import net.sourceforge.jeval.EvaluationException;

/**
 * @author zak <zak@swingpulse.com>
 */
public class JEvalEvaluator implements Evaluator {

    private net.sourceforge.jeval.Evaluator evaluator = new net.sourceforge.jeval.Evaluator();

    @Override

    public double eval(String expression) {
        try {
            return Double.parseDouble(evaluator.evaluate(expression));
        } catch (EvaluationException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
