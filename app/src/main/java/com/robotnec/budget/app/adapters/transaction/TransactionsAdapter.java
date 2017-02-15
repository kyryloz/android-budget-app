package com.robotnec.budget.app.adapters.transaction;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.robotnec.budget.core.service.aggregation.impl.TimeSpan;
import com.robotnec.budget.core.service.aggregation.impl.TransactionAggregation;

import java.util.ArrayList;
import java.util.List;

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
        setHasStableIds(true);
    }

    public void update(TransactionAggregation aggregation) {
        this.items.clear();
        this.items.addAll(toTransactionListItems(aggregation));
        notifyDataSetChanged();
    }

    private List<TransactionListItem> toTransactionListItems(TransactionAggregation aggregation) {
        List<TransactionListItem> items = new ArrayList<>();
        Stream.of(aggregation.get(TransactionAggregation.Sorting.DESC)).forEach(entry -> {
            TimeSpan span = entry.getKey();
            items.add(new HeaderItem(
                    span.getStartDate().toLocalDate(),
                    aggregation.getSum(span)));
            items.addAll(Stream.of(entry.getValue())
                    .map(TransactionItem::new)
                    .collect(Collectors.toList()));
        });
        return items;
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
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
