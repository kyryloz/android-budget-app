package com.robotnec.budget.core.domain;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import java.util.List;

/**
 * @author zak <zak@swingpulse.com>
 */
public enum Currency {
    UAH("UAH", "₴"), USD("USD", "$");

    private final String code;
    private final String symbol;

    public static Currency fromCode(String code) {
        return Stream.of(values())
                .filter(v -> v.code.equals(code))
                .single();
    }

    Currency(String code, String symbol) {
        this.code = code;
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCode() {
        return code;
    }

    public static List<String> getAllCodes() {
        return Stream.of(values())
                .map(v -> v.code)
                .collect(Collectors.toList());
    }
}
