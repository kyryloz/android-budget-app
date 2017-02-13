package com.robotnec.budget.app.adapters.transaction;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robotnec.budget.R;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneOffset;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author zak <zak@swingpulse.com>
 */
class HeaderItem implements TransactionListItem {

    private LocalDate date;
    private Context context;

    HeaderItem(LocalDate date, Context context) {
        this.date = date;
        this.context = context;
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
        long time = date.atStartOfDay().toEpochSecond(ZoneOffset.UTC) * 1000;
        String relativeDate = DateUtils.getRelativeTimeSpanString(time,
                Instant.now().toEpochMilli(),
                DateUtils.DAY_IN_MILLIS).toString();
        holder.textDate.setText(relativeDate);
    }

    @Override
    public long getId() {
        return -date.toEpochDay();
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
