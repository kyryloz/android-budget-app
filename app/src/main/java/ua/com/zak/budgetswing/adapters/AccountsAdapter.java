package ua.com.zak.budgetswing.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import ua.com.zak.budgetswing.R;
import ua.com.zak.budgetswing.core.domain.Account;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AccountsAdapter extends BaseAdapter<Account, AccountsAdapter.ViewHolder> {

    private final AccountClickListener mListener;

    public AccountsAdapter(Context context, AccountClickListener listener) {
        super(context);
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_account, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Account account = mItems.get(position);
        holder.mTextName.setText(account.getName());
        String amountStr = mContext.getString(R.string.accounts_amount_format,
                account.getAmount(),
                account.getCurrencyCode());
        holder.mTextAmount.setText(amountStr);

        holder.itemView.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onAccountClick(mItems.get(holder.getAdapterPosition()));
            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.text_name)
        TextView mTextName;

        @Bind(R.id.text_amount)
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
