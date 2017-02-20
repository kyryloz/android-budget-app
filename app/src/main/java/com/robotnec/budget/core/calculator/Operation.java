package com.robotnec.budget.core.calculator;

/**
 * @author zak <zak@swingpulse.com>
 */
public enum Operation implements Op {
    DIVIDE {
        @Override
        public double result() {
            return 0;
        }

        @Override
        public String displayText() {
            return "/";
        }
    }, MULTIPLY {
        @Override
        public double result() {
            return 0;
        }

        @Override
        public String displayText() {
            return "*";
        }
    }, MINUS {
        @Override
        public double result() {
            return 0;
        }

        @Override
        public String displayText() {
            return "-";
        }
    }, PLUS {
        @Override
        public double result() {
            return 0;
        }

        @Override
        public String displayText() {
            return "+";
        }
    }
}
