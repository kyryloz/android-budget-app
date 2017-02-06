package com.robotnec.budget.app.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.robotnec.budget.R;
import com.robotnec.budget.core.domain.Transaction;

/**
 * @author zak <zak@swingpulse.com>
 */
public class TransactionsAdapter extends BaseAdapter<Transaction, TransactionsAdapter.ViewHolder> {

    private final DateFormat dateFormat;

    public TransactionsAdapter(Context context) {
        super(context);
        dateFormat = DateFormat.getDateInstance();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_transaction, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Transaction transaction = items.get(position);
        String textAmount = transaction.getAmount().toDisplayableString();
        String textFrom = transaction.getAccount().getName();
        String textWhere = transaction.getCategory().getName();
        String textDate = dateFormat.format(new Date(transaction.getDate()));

        holder.textAmount.setText(textAmount);
        holder.textFrom.setText(textFrom);
        holder.textWhere.setText(textWhere);
        holder.textDate.setText(textDate);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_amount)
        TextView textAmount;

        @BindView(R.id.text_from)
        TextView textFrom;

        @BindView(R.id.text_where)
        TextView textWhere;

        @BindView(R.id.text_date)
        TextView textDate;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
