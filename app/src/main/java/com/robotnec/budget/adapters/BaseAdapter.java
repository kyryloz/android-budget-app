package com.robotnec.budget.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zak <zak@swingpulse.com>
 */
public abstract class BaseAdapter<T, S extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<S> {

    final Context context;
    final List<T> items;
    final LayoutInflater layoutInflater;

    BaseAdapter(Context context) {
        items = new ArrayList<>();
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void update(List<T> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }
}
