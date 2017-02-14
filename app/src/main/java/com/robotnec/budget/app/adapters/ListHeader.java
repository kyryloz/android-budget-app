package com.robotnec.budget.app.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface ListHeader {
    RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent);

    void onBindViewHolder(RecyclerView.ViewHolder holder);
}
