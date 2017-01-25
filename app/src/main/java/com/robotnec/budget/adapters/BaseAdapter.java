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

    final Context mContext;
    final List<T> mItems;
    final LayoutInflater mInflater;

    BaseAdapter(Context context) {
        mItems = new ArrayList<>();
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void update(List<T> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }
}
