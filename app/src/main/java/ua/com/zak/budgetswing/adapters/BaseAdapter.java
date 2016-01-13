package ua.com.zak.budgetswing.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zak <zak@swingpulse.com>
 */
public abstract class BaseAdapter<T, S extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<S> {

    protected final Context mContext;
    protected final List<T> mItems;
    protected final LayoutInflater mInflater;

    public BaseAdapter(Context context) {
        mItems = new ArrayList<>();
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void update(List<T> accounts) {
        mItems.clear();
        mItems.addAll(accounts);
        notifyDataSetChanged();
    }
}
