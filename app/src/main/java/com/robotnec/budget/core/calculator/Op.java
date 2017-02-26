package com.robotnec.budget.core.calculator;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface Op {

    Op DIVIDE = new Divide();
    Op MULTIPLY = new Multiply();
    Op MINUS = new Minus();
    Op PLUS = new Plus();

    String displayText();

    String symbol();

    final class Divide implements Op {

        private Divide() {}

        @Override
        public String displayText() {
            return "\u00f7";
        }

        @Override
        public String symbol() {
            return "/";
        }
    }

    final class Multiply implements Op {

        private Multiply() {}

        @Override
        public String displayText() {
            return "×";
        }

        @Override
        public String symbol() {
            return "*";
        }
    }

    final class Minus implements Op {

        private Minus() {}

        @Override
        public String displayText() {
            return "-";
        }

        @Override
        public String symbol() {
            return "-";
        }
    }

    final class Plus implements Op {

        private Plus() {}

        @Override
        public String displayText() {
            return "+";
        }

        @Override
        public String symbol() {
            return "+";
        }
    }
}
