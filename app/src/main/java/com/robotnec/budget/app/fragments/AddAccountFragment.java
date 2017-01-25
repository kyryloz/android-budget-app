package com.robotnec.budget.app.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.OnClick;
import com.robotnec.budget.R;
import com.robotnec.budget.core.domain.Account;
import com.robotnec.budget.core.mvp.presenter.AddAccountPresenter;
import com.robotnec.budget.core.mvp.view.AddAccountView;
import com.robotnec.budget.app.util.Keys;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AddAccountFragment extends BasePresenterFragment<AddAccountPresenter>
        implements AddAccountView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.edit_account_name)
    EditText editAccountName;

    @BindView(R.id.edit_account_initial_amount)
    EditText editAccountInitialAmount;

    @BindView(R.id.spinner_currency)
    Spinner spinnerAccountCurrency;

    @BindView(R.id.button_delete)
    Button buttonDelete;

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
        initToolbarBack(toolbar);
    }

    @OnClick(R.id.button_delete)
    void onDeleteClick() {
        presenter.deleteAccount();
    }

    @OnClick(R.id.button_done)
    void onDoneClick() {
        String accountName = editAccountName.getText().toString();
        String accountInitialAmount = editAccountInitialAmount.getText().toString();
        String accountCurrency = spinnerAccountCurrency.getSelectedItem().toString();
        presenter.addOrEditAccount(accountName, accountInitialAmount, accountCurrency);
        getActivity().finish();
    }

    @Override
    public void initEditMode(Account account) {
        editAccountName.setText(account.getName());
        editAccountName.setSelection(editAccountName.length());

        editAccountInitialAmount.setText(String.valueOf(account.getAmount()));
        editAccountInitialAmount.setSelection(editAccountInitialAmount.length());

        buttonDelete.setVisibility(View.VISIBLE);

        // TODO set currency spinner position
    }

    @Override
    public void closeView() {
        getActivity().finish();
    }
}
