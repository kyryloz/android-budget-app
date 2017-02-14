package com.robotnec.budget.app.adapters.support;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * @author zak <zak@swingpulse.com>
 */
public class HeaderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final static int VIEW_TYPE_HEADER = 1;
    private final static int VIEW_TYPE_BODY = 2;

    private final RecyclerView.Adapter body;
    private final ListHeader listHeader;

    public HeaderAdapter(RecyclerView.Adapter body, ListHeader listHeader) {
        this.body = body;
        this.listHeader = listHeader;
        setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        if (position == 0) {
            return -1;
        } else {
            return body.getItemId(position - 1);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ViewTypeHash.hash(VIEW_TYPE_HEADER, 0);
        } else {
            return ViewTypeHash.hash(VIEW_TYPE_BODY, body.getItemViewType(position - 1));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewTypeHash) {
        if (ViewTypeHash.getAdapterViewType(viewTypeHash) == VIEW_TYPE_HEADER) {
            return listHeader.onCreateViewHolder(parent);
        } else {
            return body.onCreateViewHolder(parent, ViewTypeHash.getItemViewType(viewTypeHash));
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            listHeader.onBindViewHolder(holder);
        } else {
            body.onBindViewHolder(holder, position - 1);
        }
    }

    @Override
    public int getItemCount() {
        return body.getItemCount() + 1;
    }

    private static class ViewTypeHash {
        static int hash(int adapterViewType, int itemViewType) {
            return adapterViewType << 8 | itemViewType;
        }

        static int getAdapterViewType(int hash) {
            return hash >> 8;
        }

        static int getItemViewType(int hash) {
            return hash & 0xFF;
        }
    }
}
