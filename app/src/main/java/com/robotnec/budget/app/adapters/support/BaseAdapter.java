package com.robotnec.budget.app.adapters.support;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zak <zak@swingpulse.com>
 */
public abstract class BaseAdapter<T, S extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<S> {

    protected final Context context;
    protected final List<T> items;
    protected final LayoutInflater layoutInflater;

    public BaseAdapter(Context context) {
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
