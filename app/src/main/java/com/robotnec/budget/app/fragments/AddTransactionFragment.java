package com.robotnec.budget.app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import com.robotnec.budget.R;
import com.robotnec.budget.core.domain.Account;
import com.robotnec.budget.core.domain.Category;
import com.robotnec.budget.core.mvp.presenter.AddTransactionPresenter;
import com.robotnec.budget.core.mvp.view.AddTransactionView;
import com.robotnec.budget.app.dialogs.AccountPickerDialogFragment;
import com.robotnec.budget.app.dialogs.CategoryPickerDialogFragment;
import com.robotnec.budget.app.dialogs.DatePickerDialogFragment;
import com.robotnec.budget.app.dialogs.PickerDialog;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AddTransactionFragment extends BasePresenterFragment<AddTransactionPresenter>
        implements AddTransactionView,
        DatePickerDialogFragment.Listener,
        AccountPickerDialogFragment.Listener,
        CategoryPickerDialogFragment.Listener {

    @BindView(R.id.layout_text_date)
    View layoutTextDate;

    @BindView(R.id.text_account_name)
    TextView textAccountName;

    @BindView(R.id.text_account_amount)
    TextView textAccountAmount;

    @BindView(R.id.text_expense_category)
    TextView textExpenseCategory;

    @BindView(R.id.text_date)
    TextView textDate;

    @BindView(R.id.edit_amount)
    EditText editAmount;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add_transaction;
    }

    @Override
    protected AddTransactionPresenter createPresenter() {
        return new AddTransactionPresenter(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_done, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_done:
                String value = editAmount.getText().toString();
                if (!TextUtils.isEmpty(value)) {
                    presenter.submit(Long.valueOf(value));
                    getActivity().finish();
                    return true;
                } else {
                    return false;
                }
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.layout_text_date)
    void onDatePickerClicked() {
        presenter.changeDate();
    }

    @OnCheckedChanged(R.id.switch_yesterday)
    void onYesterdayCheckedChanged(boolean checked) {
        presenter.setYesterdayChose(checked);
        layoutTextDate.setVisibility(checked ? View.GONE : View.VISIBLE);
    }

    @OnClick(R.id.layout_account)
    void onAccountChangeClicked() {
        presenter.changeAccount();
    }

    @OnClick(R.id.layout_expense_category)
    void onCategoryChangeClicked() {
        presenter.changeCategory();
    }

    @Override
    public void onDatePicked(Calendar calendar) {
        presenter.setChosenDate(calendar);
    }

    @Override
    public void onPicked(Account account) {
        presenter.pickAccount(account);
    }

    @Override
    public void onPicked(Category category) {
        presenter.pickCategory(category);
    }

    @Override
    public void initDatePickerButton(String date) {
        textDate.setText(date);
    }

    @Override
    public void showAccountsChooserDialog(List<Account> accounts) {
        DialogFragment dialog =
                AccountPickerDialogFragment.newInstance(
                        accounts,
                        this);
        dialog.show(getFragmentManager(), PickerDialog.TAG);
    }

    @Override
    public void showCategoryChooserDialog(List<Category> categories) {
        DialogFragment dialog =
                CategoryPickerDialogFragment.newInstance(
                        categories,
                        this);
        dialog.show(getFragmentManager(), PickerDialog.TAG);
    }

    @Override
    public void showChosenDate(String date) {
        textDate.setText(date);
    }

    @Override
    public void showDateChooserDialog(Calendar lastDate) {
        DatePickerDialogFragment datePicker = DatePickerDialogFragment.newInstance(lastDate, this);
        datePicker.show(getFragmentManager(), DatePickerDialogFragment.TAG);
    }

    @Override
    public void displayAccount(Account account) {
        textAccountName.setText(account.getName());
        textAccountAmount.setText(getString(
                R.string.accounts_amount_format,
                account.getAmount(),
                account.getCurrencyId() + "id"));
    }

    @Override
    public void displayCategory(Category category) {
        textExpenseCategory.setText(category.getName());
    }

    @Override
    public void finish() {
        getActivity().finish();
    }
}
