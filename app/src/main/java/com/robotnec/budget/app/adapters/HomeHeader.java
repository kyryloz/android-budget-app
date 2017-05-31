package com.robotnec.budget.app.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robotnec.budget.R;
import com.robotnec.budget.app.adapters.support.ListHeader;
import com.robotnec.budget.core.domain.Account;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author zak <zak@swingpulse.com>
 */
public class HomeHeader implements ListHeader {

    private final List<Account> accounts;

    public HomeHeader() {
        accounts = new ArrayList<>();
    }

    public void update(List<Account> accounts) {
        this.accounts.clear();
        int toIndex = accounts.size() > 2 ? 2 : accounts.size();
        this.accounts.addAll(accounts.subList(0, toIndex));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.view_home_header, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder) {
        if (accounts.isEmpty()) {
            return;
        }
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.textAmount.setText(accounts.get(0).getAmount().toDisplayableString());
        viewHolder.textAmountSecond.setText(accounts.get(1).getAmount().toDisplayableString());
        viewHolder.textName.setText(accounts.get(0).getName());
        viewHolder.textNameSecond.setText(accounts.get(1).getName());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_name)
        TextView textName;

        @BindView(R.id.textAmount)
        TextView textAmount;

        @BindView(R.id.text_name_second)
        TextView textNameSecond;

        @BindView(R.id.text_amount_second)
        TextView textAmountSecond;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
