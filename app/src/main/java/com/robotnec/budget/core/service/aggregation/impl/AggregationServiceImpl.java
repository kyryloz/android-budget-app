package com.robotnec.budget.core.service.aggregation.impl;

import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;
import com.robotnec.budget.core.domain.operation.MoneyOperation;
import com.robotnec.budget.core.service.aggregation.AggregationService;
import com.robotnec.budget.core.service.aggregation.TransactionAggregation;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;

import java.util.Comparator;
import java.util.List;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AggregationServiceImpl implements AggregationService {

    @Override
    public TransactionAggregation aggregate(List<MoneyOperation> transactions, Resolution resolution) {
        Comparator<MoneyOperation> comparator = (o1, o2) -> (int) (o1.getDate() - o2.getDate());
        Function<MoneyOperation, Long> date = MoneyOperation::getDate;
        long minTimestamp = Stream.of(transactions)
                .min(comparator)
                .map(date)
                .get();
        long maxTimestamp = Stream.of(transactions)
                .max(comparator)
                .map(date)
                .get();

        LocalDate minDate = LocalDate.from(Instant.ofEpochMilli(minTimestamp));
        LocalDate maxDate = LocalDate.from(Instant.ofEpochMilli(maxTimestamp));

        TimeSpans timeSpans = TimeSpans.dyDays(minDate, maxDate);

        return null;
    }

}
