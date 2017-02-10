package com.robotnec.budget.core.service.aggregation.impl;

import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;
import com.robotnec.budget.core.domain.operation.Transaction;
import com.robotnec.budget.core.service.aggregation.AggregationService;

import org.threeten.bp.LocalDateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AggregationServiceImpl implements AggregationService {

    @Override
    public TransactionAggregation aggregate(List<Transaction> transactions, Resolution resolution) {
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

        Map<TimeSpan, List<Transaction>> aggregatedMap = new HashMap<>();
        for (TimeSpan timeSpan : timeSpans) {
            aggregatedMap.put(timeSpan, new ArrayList<>());
            for (Transaction transaction : transactions) {
                if (timeSpan.isInSpan(transaction.getDate())) {
                    List<Transaction> values = aggregatedMap.get(timeSpan);
                    values.add(transaction);
                }
            }
        }

        return TransactionAggregationImpl.from(aggregatedMap);
    }

}
