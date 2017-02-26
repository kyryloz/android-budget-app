package com.robotnec.budget.core.calculator;

import android.text.TextUtils;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * @author zak <zak@swingpulse.com>
 */
class Input {

    private Queue<Entry> inputStack = new LinkedList<>();

    Input() {
        clear();
    }

    void append(String symbol) {
        inputStack.add(new Entry(symbol, symbol, false));
    }

    void append(Op op) {
        inputStack.add(new Entry(op.displayText(), op.symbol(), true));
    }

    void replace(String symbol) {
        inputStack.clear();
        inputStack.add(new Entry(symbol, symbol, false));
    }

    Queue<Entry> getInputStack() {
        return inputStack;
    }

    void delete() {
        inputStack.remove();
    }

    void clear() {
        inputStack.clear();
    }

    String toDisplayText() {
        String text = TextUtils.join("", Stream.of(inputStack)
                .map(Entry::displayText)
                .collect(Collectors.toList()));
        return TextUtils.isEmpty(text) ? "0" : text;
    }

    String toExpression() {
        return TextUtils.join("", Stream.of(inputStack)
                .map(Entry::symbol)
                .collect(Collectors.toList()));
    }

    static class Entry {
        private final String displayText;
        private final String symbol;
        private final boolean isOperation;

        Entry(String displayText, String symbol, boolean isOperation) {
            Objects.requireNonNull(displayText);
            Objects.requireNonNull(symbol);
            this.displayText = displayText;
            this.symbol = symbol;
            this.isOperation = isOperation;
        }

        String displayText() {
            return displayText;
        }

        String symbol() {
            return symbol;
        }

        boolean isOperation() {
            return isOperation;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Entry entry = (Entry) o;

            if (isOperation != entry.isOperation) return false;
            if (!displayText.equals(entry.displayText)) return false;
            return symbol.equals(entry.symbol);

        }

        @Override
        public int hashCode() {
            int result = displayText.hashCode();
            result = 31 * result + symbol.hashCode();
            result = 31 * result + (isOperation ? 1 : 0);
            return result;
        }
    }
}
