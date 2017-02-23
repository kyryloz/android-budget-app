package com.robotnec.budget.core.calculator;

import android.support.annotation.NonNull;

/**
 * @author zak <zak@swingpulse.com>
 */
abstract class Op implements Comparable<Op> {

    abstract double calculate(double firstOperand, double secondOperand);

    abstract String displayText();

    abstract int getOrder();

    @Override
    public int compareTo(@NonNull Op o) {
        return getOrder() - o.getOrder();
    }

    class Divide extends Op {
        @Override
        public double calculate(double firstOperand, double secondOperand) {
            return firstOperand / secondOperand;
        }

        @Override
        public String displayText() {
            return "/";
        }

        @Override
        public int getOrder() {
            return 0;
        }
    }

    class Multiply extends Op {
        @Override
        public double calculate(double firstOperand, double secondOperand) {
            return firstOperand * secondOperand;
        }

        @Override
        public String displayText() {
            return "*";
        }

        @Override
        public int getOrder() {
            return 0;
        }
    }

    class Minus extends Op {
        @Override
        public double calculate(double firstOperand, double secondOperand) {
            return firstOperand - secondOperand;
        }

        @Override
        public String displayText() {
            return "-";
        }

        @Override
        public int getOrder() {
            return 1;
        }
    }

    class Plus extends Op {
        @Override
        public double calculate(double firstOperand, double secondOperand) {
            return firstOperand + secondOperand;
        }

        @Override
        public String displayText() {
            return "+";
        }

        @Override
        public int getOrder() {
            return 1;
        }
    }
}
