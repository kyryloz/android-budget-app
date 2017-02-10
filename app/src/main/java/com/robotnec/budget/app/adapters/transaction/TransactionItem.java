package com.robotnec.budget.app.adapters.transaction;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robotnec.budget.R;
import com.robotnec.budget.core.domain.operation.Transaction;

import java.text.DateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author zak <zak@swingpulse.com>
 */
class TransactionItem implements TransactionListItem {

    private final Transaction transaction;
    private final DateFormat dateFormat;

    static RecyclerView.ViewHolder createViewHolder(LayoutInflater inflater, ViewGroup parent) {
        View itemView = inflater.inflate(R.layout.item_transaction, parent, false);
        return new TransactionViewHolder(itemView);
    }

    TransactionItem(Transaction transaction) {
        this.transaction = transaction;
        dateFormat = DateFormat.getDateInstance();
    }

    @Override
    public int getViewType() {
        return TransactionsAdapter.VIEW_TYPE_ITEM;
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder viewHolder) {
        TransactionViewHolder holder = (TransactionViewHolder) viewHolder;
        String textAmount = transaction.getAmount().toDisplayableString();
        String textFrom = transaction.getAccount().getName();
        String textWhere = transaction.getCategory().getName();
        String textDate = dateFormat.format(new Date(transaction.getDate()));

        holder.textAmount.setText(textAmount);
        holder.textFrom.setText(textFrom);
        holder.textWhere.setText(textWhere);
        holder.textDate.setText(textDate);
    }

    static class TransactionViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_amount)
        TextView textAmount;

        @BindView(R.id.text_from)
        TextView textFrom;

        @BindView(R.id.text_where)
        TextView textWhere;

        @BindView(R.id.text_date)
        TextView textDate;

        TransactionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
