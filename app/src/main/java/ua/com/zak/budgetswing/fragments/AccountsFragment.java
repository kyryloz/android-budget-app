package ua.com.zak.budgetswing.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.Bind;
import ua.com.zak.budgetswing.R;
import ua.com.zak.budgetswing.adapters.AccountsAdapter;
import ua.com.zak.budgetswing.model.dao.AccountDao;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AccountsFragment extends BaseFragment {

    @Bind(R.id.recycler_accounts)
    RecyclerView mRecyclerAccounts;

    private AccountDao mAccountDao;

    private AccountsAdapter mAccountsAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAccountDao = mApplicationModel.getDaoFactory().getAccountDao();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_accounts_summary;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initAccountsList();
    }

    @Override
    public void onResume() {
        super.onResume();
        mAccountsAdapter.update(mAccountDao.getAllAccounts());
    }

    private void initAccountsList() {
        mAccountsAdapter = new AccountsAdapter(getContext());
        mRecyclerAccounts.setAdapter(mAccountsAdapter);
        mRecyclerAccounts.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
