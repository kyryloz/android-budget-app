package com.robotnec.budget.app.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * @author zak <zak@swingpulse.com>
 */
public class MergeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final static int VIEW_TYPE_FIRST = 1;
    private final static int VIEW_TYPE_SECOND = 2;

    private final RecyclerView.Adapter first;
    private final RecyclerView.Adapter second;

    public MergeAdapter(RecyclerView.Adapter first, RecyclerView.Adapter second) {

        this.first = first;
        this.second = second;
        setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        RecyclerView.Adapter target = findTargetAdapter(position);
        int normalPosition = normalizePosition(target, position);
        return target.getItemId(normalPosition);
    }

    @Override
    public int getItemViewType(int position) {
        RecyclerView.Adapter target = findTargetAdapter(position);
        int normalPosition = normalizePosition(target, position);
        int itemViewType = target.getItemViewType(normalPosition);
        int adapterViewType = target == first ? VIEW_TYPE_FIRST : VIEW_TYPE_SECOND;
        return ViewTypeHash.hash(adapterViewType, itemViewType);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewTypeHash) {
        RecyclerView.Adapter target;
        target = ViewTypeHash.getAdapterViewType(viewTypeHash) == VIEW_TYPE_FIRST ? first : second;
        return target.onCreateViewHolder(parent, ViewTypeHash.getItemViewType(viewTypeHash));
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RecyclerView.Adapter target = findTargetAdapter(position);
        int normalPosition = normalizePosition(target, position);
        target.onBindViewHolder(holder, normalPosition);
    }

    @Override
    public int getItemCount() {
        return first.getItemCount() + second.getItemCount();
    }

    private RecyclerView.Adapter findTargetAdapter(int position) {
        RecyclerView.Adapter target = first;
        if (position >= first.getItemCount()) {
            target = second;
        }
        return target;
    }

    private int normalizePosition(RecyclerView.Adapter target, int position) {
        if (target == first) {
            return position;
        }
        return position - first.getItemCount();
    }

    private static class ViewTypeHash {
        static int hash(int adapterViewType, int itemviewType) {
            return adapterViewType << 8 | itemviewType;
        }

        static int getAdapterViewType(int hash) {
            return hash >> 8;
        }

        static int getItemViewType(int hash) {
            return hash & 0xFF;
        }
    }
}
