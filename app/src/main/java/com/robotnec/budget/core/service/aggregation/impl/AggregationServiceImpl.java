package com.robotnec.budget.core.service.aggregation.impl;

import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;
import com.robotnec.budget.core.domain.operation.Transaction;
import com.robotnec.budget.core.service.aggregation.AggregationService;

import org.threeten.bp.LocalDateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AggregationServiceImpl implements AggregationService {

    @Override
    public TransactionAggregation aggregate(List<Transaction> transactions, Resolution resolution) {
        TreeMap<TimeSpan, List<Transaction>> aggregatedMap = new TreeMap<>();
        if (transactions.isEmpty()) {
            return TransactionAggregationImpl.from(aggregatedMap);
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
            ArrayList<Transaction> values = new ArrayList<>();
            aggregatedMap.put(timeSpan, values);
            for (Transaction transaction : transactions) {
                if (timeSpan.isInSpan(transaction.getDate())) {
                    values.add(transaction);
                }
            }
            if (values.isEmpty()) {
                aggregatedMap.remove(timeSpan);
            }
        }
        return TransactionAggregationImpl.from(aggregatedMap);
    }
}
