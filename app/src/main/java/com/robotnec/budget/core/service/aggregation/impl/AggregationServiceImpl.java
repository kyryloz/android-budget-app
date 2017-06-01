package com.robotnec.budget.core.service.aggregation.impl;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;
import com.robotnec.budget.core.domain.Currency;
import com.robotnec.budget.core.domain.MoneyAmount;
import com.robotnec.budget.core.domain.operation.Transaction;
import com.robotnec.budget.core.service.aggregation.AggregationService;

import org.threeten.bp.LocalDateTime;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AggregationServiceImpl implements AggregationService {

    @Override
    public TransactionAggregation aggregate(List<Transaction> transactions, Resolution resolution) {
        SortedMap<TimeSpan, List<Transaction>> aggregatedMap = new TreeMap<>();
        Map<TimeSpan, MoneyAmount> sums = new HashMap<>();
        if (transactions.isEmpty()) {
            return TransactionAggregationImpl.from(aggregatedMap, sums);
        }

        Comparator<Transaction> comparator = (o1, o2) -> o1.getDate().compareTo(o2.getDate());
        Function<Transaction, LocalDateTime> date = Transaction::getDate;
        LocalDateTime minDate = Stream.of(transactions)
                .min(comparator)
                .map(date)
                .get();
        LocalDateTime maxDate = Stream.of(transactions)
                .max(comparator)
                .map(date)
                .get();

        TimeSpans timeSpans = TimeSpans.dyDays(minDate, maxDate);

        Collections.sort(transactions, comparator);

        for (TimeSpan timeSpan : timeSpans) {
            List<Transaction> values = Stream.of(transactions)
                    .filter(t -> timeSpan.isInSpan(t.getDate()))
                    .collect(Collectors.toList());
            MoneyAmount sum = Stream.of(transactions)
                    .map(Transaction::getAmount)
                    .reduce(MoneyAmount.Companion.of(0, Currency.UAH), MoneyAmount::add);
            if (!values.isEmpty()) {
                aggregatedMap.put(timeSpan, values);
                sums.put(timeSpan, sum);
            }
        }

        return TransactionAggregationImpl.from(aggregatedMap, sums);
    }
}
