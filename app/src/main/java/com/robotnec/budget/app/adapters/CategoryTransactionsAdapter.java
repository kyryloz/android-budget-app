package com.robotnec.budget.app.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.robotnec.budget.core.domain.money.MoneyOperation;

/**
 * @author zak <zak@swingpulse.com>
 */
public class CategoryTransactionsAdapter extends BaseAdapter<MoneyOperation, CategoryTransactionsAdapter.ViewHolder> {

    CategoryTransactionsAdapter(Context context) {
        super(context);
    }

    @Override
    public CategoryTransactionsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CategoryTransactionsAdapter.ViewHolder holder, int position) {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
