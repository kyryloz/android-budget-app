package ua.com.zak.budgetswing.adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ua.com.zak.budgetswing.R;
import ua.com.zak.budgetswing.model.domen.Account;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AccountsAdapter extends RecyclerView.Adapter<AccountsAdapter.ViewHolder> {

    private final LayoutInflater mInflater;
    private final List<Account> mAccountList;
    private final Context mContext;

    public AccountsAdapter(Context context) {
        mAccountList = new ArrayList<>();
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_account_summary, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Account account = mAccountList.get(position);
        holder.mTextName.setText(account.getName());
        String amountStr = mContext.getString(R.string.accounts_amount_format,
                account.getAmount(),
                account.getCurrencyCode());
        holder.mTextAmount.setText(amountStr);
    }

    @Override
    public int getItemCount() {
        return mAccountList.size();
    }

    public void update(List<Account> accounts) {
        mAccountList.clear();
        mAccountList.addAll(accounts);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.text_name)
        AppCompatTextView mTextName;

        @Bind(R.id.text_amount)
        AppCompatTextView mTextAmount;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
