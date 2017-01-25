package com.robotnec.budget.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.robotnec.budget.R;
import com.robotnec.budget.core.domain.Transaction;

/**
 * @author zak <zak@swingpulse.com>
 */
public class TransactionsAdapter extends BaseAdapter<Transaction, TransactionsAdapter.ViewHolder> {

    private final DateFormat mDateFormat;

    public TransactionsAdapter(Context context) {
        super(context);
        mDateFormat = DateFormat.getDateInstance();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_transaction, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Transaction transaction = mItems.get(position);
        long amount = transaction.getAmount();
        String currency = transaction.getCurrency();
        String textFrom = transaction.getAccount().getName();
        String textWhere = transaction.getCategory().getName();
        String textDate = mDateFormat.format(new Date(transaction.getDate()));

        holder.mTextAmount.setText(String.valueOf(Math.abs(amount)) + " " + currency);
        holder.mTextFrom.setText(textFrom);
        holder.mTextWhere.setText(textWhere);
        holder.mTextDate.setText(textDate);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.text_amount)
        TextView mTextAmount;

        @Bind(R.id.text_from)
        TextView mTextFrom;

        @Bind(R.id.text_where)
        TextView mTextWhere;

        @Bind(R.id.text_date)
        TextView mTextDate;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
