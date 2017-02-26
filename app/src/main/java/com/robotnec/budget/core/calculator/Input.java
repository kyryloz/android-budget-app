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
    private static final Entry INIT_VALUE = new Entry("0", "0");

    private Queue<Entry> inputStack = new LinkedList<>();

    Input() {
        clear();
    }

    void append(String symbol) {
        inputStack.add(new Entry(symbol, symbol));
    }

    void append(Op op) {
        inputStack.add(new Entry(op.displayText(), op.symbol()));
    }

    void replace(String symbol) {
        inputStack.clear();
        inputStack.add(new Entry(symbol, symbol));
    }

    void dot() {
        String dot = ".";
        if (!inputStack.contains(new Entry(".", "."))) {
            append(dot);
        }
    }

    boolean delete() {
        boolean deletedLastSymbol;
        if (inputStack.size() == 1) {
            clear();
            deletedLastSymbol = true;
        } else {
            inputStack.remove();
            deletedLastSymbol = false;
        }
        return deletedLastSymbol;
    }

    boolean lastSymbolIsDigit() {
        return TextUtils.isDigitsOnly(inputStack.element().symbol);
    }

    void clear() {
        inputStack.clear();
        inputStack.add(INIT_VALUE);
    }

    String toDisplayText() {
        return TextUtils.join("", Stream.of(inputStack)
                .map(Entry::displayText)
                .collect(Collectors.toList()));
    }

    String toExpression() {
        return TextUtils.join("", Stream.of(inputStack)
                .map(Entry::symbol)
                .collect(Collectors.toList()));
    }

    private static class Entry {
        private final String displayText;
        private final String symbol;

        private Entry(String displayText, String symbol) {
            Objects.requireNonNull(displayText);
            Objects.requireNonNull(symbol);
            this.displayText = displayText;
            this.symbol = symbol;
        }

        String displayText() {
            return displayText;
        }

        String symbol() {
            return symbol;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Entry entry = (Entry) o;

            if (!displayText.equals(entry.displayText)) return false;
            return symbol.equals(entry.symbol);

        }

        @Override
        public int hashCode() {
            int result = displayText.hashCode();
            result = 31 * result + symbol.hashCode();
            return result;
        }
    }
}
