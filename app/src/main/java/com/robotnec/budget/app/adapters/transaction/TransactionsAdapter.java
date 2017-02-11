package com.robotnec.budget.app.adapters.transaction;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.robotnec.budget.core.domain.operation.Transaction;
import com.robotnec.budget.core.service.aggregation.impl.TimeSpan;
import com.robotnec.budget.core.service.aggregation.impl.TransactionAggregation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

/**
 * @author zak <zak@swingpulse.com>
 */
public class TransactionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    static final int VIEW_TYPE_HEADER = 1;
    static final int VIEW_TYPE_ITEM = 2;

    private final List<TransactionListItem> items;
    private LayoutInflater layoutInflater;

    public TransactionsAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
        items = new ArrayList<>();
    }

    public void setItems(TransactionAggregation aggregation) {
        this.items.clear();
        this.items.addAll(toTransactionListItems(aggregation));
    }

    private List<TransactionListItem> toTransactionListItems(TransactionAggregation aggregation) {
        List<TransactionListItem> items = new ArrayList<>();
        SortedMap<TimeSpan, List<Transaction>> map =
                aggregation.getMap(TransactionAggregation.Sorting.DESC);

        for (Map.Entry<TimeSpan, List<Transaction>> entry : map.entrySet()) {
            items.add(new HeaderItem(entry.getKey().getStartDate().toLocalDate()));
            List<Transaction> transactions = entry.getValue();
            for (Transaction transaction : transactions) {
                items.add(new TransactionItem(transaction));
            }
        }
        return items;
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getViewType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_HEADER) {
            return HeaderItem.createViewHolder(layoutInflater, parent);
        } else {
            return TransactionItem.createViewHolder(layoutInflater, parent);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        items.get(position).bindViewHolder(holder);
    }
}
