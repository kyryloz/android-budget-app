package com.robotnec.budget.app.adapters.transaction;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robotnec.budget.R;
import com.robotnec.budget.app.util.DateUtil;
import com.robotnec.budget.core.domain.MoneyAmount;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.format.TextStyle;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author zak <zak@swingpulse.com>
 */
class HeaderItem implements TransactionListItem {

    private final LocalDate date;
    private final MoneyAmount sum;

    HeaderItem(LocalDate date, MoneyAmount sum) {
        this.date = date;
        this.sum = sum;
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
        holder.textDayOfMonth.setText(getDayOfMonth());
        holder.textRelativeDate.setText(getRelativeDate());
        holder.textMonth.setText(getMonth());
        holder.textSum.setText(getSum());
    }

    @NonNull
    private String getRelativeDate() {
        LocalDate twoDaysAgo = Instant.now().atOffset(ZoneOffset.UTC).minusDays(2).toLocalDate();
        if (date.isAfter(twoDaysAgo)) {
            long time = DateUtil.toSeconds(date.atStartOfDay()) * 1000;
            return DateUtils.getRelativeTimeSpanString(time,
                    Instant.now().toEpochMilli(),
                    DateUtils.DAY_IN_MILLIS).toString();
        } else {
            return date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
        }
    }

    @NonNull
    private String getDayOfMonth() {
        return String.valueOf(date.getDayOfMonth());
    }

    @NonNull
    private String getMonth() {
        return date.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
    }

    private String getSum() {
        return "-" + sum.toDisplayableString();
    }

    @Override
    public long getId() {
        return -date.toEpochDay();
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_day_of_month)
        TextView textDayOfMonth;

        @BindView(R.id.text_relative_day)
        TextView textRelativeDate;

        @BindView(R.id.text_month)
        TextView textMonth;

        @BindView(R.id.text_sum)
        TextView textSum;

        HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
