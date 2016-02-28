package ua.com.zak.budgetswing.fragments;

import android.support.v4.app.Fragment;

import ua.com.zak.budgetswing.R;
import ua.com.zak.budgetswing.core.mvp.presenter.TransactionsPresenter;
import ua.com.zak.budgetswing.core.mvp.view.TransactionsView;

/**
 * @author zak <zak@swingpulse.com>
 */
public class TransactionsFragment extends BasePresenterFragment<TransactionsPresenter> implements TransactionsView {

    public static Fragment newInstance() {
        return new TransactionsFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_transactions;
    }

    @Override
    protected TransactionsPresenter createPresenter() {
        return new TransactionsPresenter(this);
    }
}
