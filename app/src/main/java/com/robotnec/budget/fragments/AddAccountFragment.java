package com.robotnec.budget.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import butterknife.Bind;
import butterknife.OnClick;
import com.robotnec.budget.R;
import com.robotnec.budget.core.domain.Account;
import com.robotnec.budget.core.mvp.presenter.AddAccountPresenter;
import com.robotnec.budget.core.mvp.view.AddAccountView;
import com.robotnec.budget.util.Keys;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AddAccountFragment extends BasePresenterFragment<AddAccountPresenter>
        implements AddAccountView {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.edit_account_name)
    EditText mEditAccountName;

    @Bind(R.id.edit_account_initial_amount)
    EditText mEditAccountInitialAmount;

    @Bind(R.id.spinner_currency)
    Spinner mSpinnerAccountCurrency;

    @Bind(R.id.button_delete)
    Button mButtonDelete;

    public static AddAccountFragment newInstance(Account account) {
        AddAccountFragment fragment = new AddAccountFragment();
        Bundle args = new Bundle();
        args.putSerializable(Keys.ACCOUNT, account);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add_account;
    }

    @Override
    protected AddAccountPresenter createPresenter() {
        Account account = (Account) getArguments().getSerializable(Keys.ACCOUNT);
        return new AddAccountPresenter(this, account);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbarBack(mToolbar);
    }

    @OnClick(R.id.button_delete)
    void onDeleteClick() {
        mPresenter.deleteAccount();
    }

    @OnClick(R.id.button_done)
    void onDoneClick() {
        String accountName = mEditAccountName.getText().toString();
        String accountInitialAmount = mEditAccountInitialAmount.getText().toString();
        String accountCurrency = mSpinnerAccountCurrency.getSelectedItem().toString();
        mPresenter.addOrEditAccount(accountName, accountInitialAmount, accountCurrency);
        getActivity().finish();
    }

    @Override
    public void initEditMode(Account account) {
        mEditAccountName.setText(account.getName());
        mEditAccountName.setSelection(mEditAccountName.length());

        mEditAccountInitialAmount.setText(String.valueOf(account.getAmount()));
        mEditAccountInitialAmount.setSelection(mEditAccountInitialAmount.length());

        mButtonDelete.setVisibility(View.VISIBLE);

        // TODO set currency spinner position
    }

    @Override
    public void closeView() {
        getActivity().finish();
    }
}
