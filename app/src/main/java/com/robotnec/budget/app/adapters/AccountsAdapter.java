package com.robotnec.budget.app.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robotnec.budget.R;
import com.robotnec.budget.core.domain.Account;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AccountsAdapter extends BaseAdapter<Account, AccountsAdapter.ViewHolder> {

    private final AccountClickListener accountClickListener;

    public AccountsAdapter(Context context, AccountClickListener listener) {
        super(context);
        accountClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_account, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Account account = items.get(position);
        holder.mTextName.setText(account.getName());
        String amountStr = context.getString(R.string.accounts_amount_format,
                account.getAmount().toPlainString(),
                account.getCurrencyId() + "id");
        holder.mTextAmount.setText(amountStr);

        holder.itemView.setOnClickListener(v -> {
            if (accountClickListener != null) {
                accountClickListener.onAccountClick(items.get(holder.getAdapterPosition()));
            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_name)
        TextView mTextName;

        @BindView(R.id.text_amount)
        TextView mTextAmount;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface AccountClickListener {
        void onAccountClick(Account account);
    }
}
