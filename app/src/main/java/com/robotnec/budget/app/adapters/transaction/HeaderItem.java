package com.robotnec.budget.app.adapters.transaction;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robotnec.budget.R;

import org.threeten.bp.LocalDate;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author zak <zak@swingpulse.com>
 */
class HeaderItem implements TransactionListItem {

    private LocalDate date;

    HeaderItem(LocalDate date) {
        this.date = date;
    }

    static RecyclerView.ViewHolder createViewHolder(LayoutInflater inflater, ViewGroup parent) {
        View itemView = inflater.inflate(R.layout.item_transaction_header, parent, false);
        return new HeaderViewHolder(itemView);
    }

    @Override
    public int getViewType() {
        return TransactionsAdapter.VIEW_TYPE_HEADER;
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder viewHolder) {
        HeaderViewHolder holder = (HeaderViewHolder) viewHolder;
        holder.textDate.setText(date.toString());
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_date)
        TextView textDate;

        HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
