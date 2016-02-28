package ua.com.zak.budgetswing.fragments;

import ua.com.zak.budgetswing.R;
import ua.com.zak.budgetswing.core.mvp.presenter.TransactionsPresenter;
import ua.com.zak.budgetswing.core.mvp.view.TransactionsView;

/**
 * @author zak <zak@swingpulse.com>
 */
public class TransactionsFragment extends BasePresenterFragment<TransactionsPresenter> implements TransactionsView {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_transactions;
    }

    @Override
    protected TransactionsPresenter createPresenter() {
        return null;
    }
}
