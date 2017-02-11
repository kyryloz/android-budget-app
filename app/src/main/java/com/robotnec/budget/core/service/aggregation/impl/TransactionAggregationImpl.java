package com.robotnec.budget.core.service.aggregation.impl;

import com.robotnec.budget.core.domain.operation.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author zak <zak@swingpulse.com>
 */
public class TransactionAggregationImpl implements TransactionAggregation {

    private final SortedMap<TimeSpan, List<Transaction>> aggregation;

    public static TransactionAggregationImpl from(SortedMap<TimeSpan, List<Transaction>> aggregation) {
        return new TransactionAggregationImpl(aggregation);
    }

    private TransactionAggregationImpl(SortedMap<TimeSpan, List<Transaction>> aggregation) {
        this.aggregation = aggregation;
        for (TimeSpan timeSpan : this.aggregation.keySet()) {
            List<Transaction> unmodifiable = Collections.unmodifiableList(this.aggregation.get(timeSpan));
            this.aggregation.put(timeSpan, unmodifiable);
        }
    }

    @Override
    public int getSpansCount() {
        return aggregation.size();
    }

    @Override
    public SortedMap<TimeSpan, List<Transaction>> getMap(Sorting sorting) {
        if (sorting == Sorting.ASC) {
            return Collections.unmodifiableSortedMap(aggregation);
        } else {
            SortedMap<TimeSpan, List<Transaction>> sortedMap = new TreeMap<>((o1, o2) -> o2.compareTo(o1));
            sortedMap.putAll(aggregation);
            for (TimeSpan timeSpan : sortedMap.keySet()) {
                List<Transaction> sortedTransactions = new ArrayList<>(sortedMap.get(timeSpan));
                Collections.sort(sortedTransactions, (o1, o2) -> o2.getDate().compareTo(o1.getDate()));
                sortedMap.put(timeSpan, sortedTransactions);
            }
            return Collections.unmodifiableSortedMap(sortedMap);
        }
    }
}
