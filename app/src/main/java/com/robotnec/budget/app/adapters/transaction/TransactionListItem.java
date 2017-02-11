package com.robotnec.budget.app.adapters.transaction;

import android.support.v7.widget.RecyclerView;

/**
 * @author zak <zak@swingpulse.com>
 */
interface TransactionListItem {
    int getViewType();

    void bindViewHolder(RecyclerView.ViewHolder viewHolder);

    long getId();
}
