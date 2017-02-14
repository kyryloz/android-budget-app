package com.robotnec.budget.app.adapters.transaction;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.robotnec.budget.R;
import com.robotnec.budget.app.util.TextIconUtils;
import com.robotnec.budget.core.domain.operation.Transaction;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author zak <zak@swingpulse.com>
 */
class TransactionItem implements TransactionListItem {

    private final Transaction transaction;

    static RecyclerView.ViewHolder createViewHolder(LayoutInflater inflater, ViewGroup parent) {
        View itemView = inflater.inflate(R.layout.item_transaction, parent, false);
        return new TransactionViewHolder(itemView);
    }

    TransactionItem(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public int getViewType() {
        return TransactionsAdapter.VIEW_TYPE_ITEM;
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder viewHolder) {
        TransactionViewHolder holder = (TransactionViewHolder) viewHolder;
        String textAmount = "-" + transaction.getAmount().toDisplayableString();
        String textAccountName = transaction.getAccount().getName();
        String textCategoryName = transaction.getCategory().getName();
        TextDrawable icon = TextIconUtils.generate(textCategoryName);

        holder.imageIcon.setImageDrawable(icon);
        holder.textAmount.setText(textAmount);
        holder.textAccountName.setText(textAccountName);
        holder.textCategoryName.setText(textCategoryName);

        Context context = viewHolder.itemView.getContext();
        // TODO operation sign
//        if (transaction.getAmount().isNegative()) {
            holder.textAmount.setTextColor(ContextCompat.getColor(context, android.R.color.holo_red_dark));
//        } else {
//            holder.textAmount.setTextColor(ContextCompat.getColor(context, android.R.color.white));
//        }
    }

    @Override
    public long getId() {
        return transaction.getId();
    }

    static class TransactionViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_icon)
        ImageView imageIcon;

        @BindView(R.id.text_amount)
        TextView textAmount;

        @BindView(R.id.text_account_name)
        TextView textAccountName;

        @BindView(R.id.text_category_name)
        TextView textCategoryName;

        TransactionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
