package com.robotnec.budget.core.service.aggregation.impl;

import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;
import com.robotnec.budget.core.domain.operation.Transaction;
import com.robotnec.budget.core.service.aggregation.AggregationService;
import com.robotnec.budget.core.service.aggregation.TransactionAggregation;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDateTime;

import java.util.Comparator;
import java.util.List;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AggregationServiceImpl implements AggregationService {

    @Override
    public TransactionAggregation aggregate(List<Transaction> transactions, Resolution resolution) {
        Comparator<Transaction> comparator = (o1, o2) -> (int) (o1.getDate() - o2.getDate());
        Function<Transaction, Long> date = Transaction::getDate;
        long minTimestamp = Stream.of(transactions)
                .min(comparator)
                .map(date)
                .get();
        long maxTimestamp = Stream.of(transactions)
                .max(comparator)
                .map(date)
                .get();

        LocalDateTime minDate = LocalDateTime.from(Instant.ofEpochMilli(minTimestamp));
        LocalDateTime maxDate = LocalDateTime.from(Instant.ofEpochMilli(maxTimestamp));

        TimeSpans timeSpans = TimeSpans.dyDays(minDate, maxDate);

        return null;
    }

}
