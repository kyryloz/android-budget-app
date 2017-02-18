package com.robotnec.budget.app.fragments;

import android.support.v4.app.DialogFragment;
import android.widget.TextView;

import com.robotnec.budget.R;
import com.robotnec.budget.app.dialogs.AccountPickerDialogFragment;
import com.robotnec.budget.app.dialogs.CategoryPickerDialogFragment;
import com.robotnec.budget.app.dialogs.DatePickerDialogFragment;
import com.robotnec.budget.app.dialogs.PickerDialog;
import com.robotnec.budget.core.domain.Account;
import com.robotnec.budget.core.domain.Category;
import com.robotnec.budget.core.domain.MoneyAmount;
import com.robotnec.budget.core.mvp.presenter.AddTransactionPresenter;
import com.robotnec.budget.core.mvp.view.AddTransactionView;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AddTransactionFragment extends BasePresenterFragment<AddTransactionPresenter>
        implements AddTransactionView,
        DatePickerDialogFragment.Listener,
        AccountPickerDialogFragment.Listener,
        CategoryPickerDialogFragment.Listener {

    @BindView(R.id.text_amount)
    TextView textAmount;

    @BindView(R.id.text_account_name)
    TextView textAccountName;

    @BindView(R.id.text_account_amount)
    TextView textAccountAmount;

    @BindView(R.id.text_expense_category)
    TextView textExpenseCategory;

    @BindView(R.id.text_date)
    TextView textDate;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add_transaction;
    }

    @Override
    protected AddTransactionPresenter createPresenter() {
        return new AddTransactionPresenter(this);
    }

    @OnClick(R.id.layout_date)
    void onDatePickerClicked() {
        presenter.changeDate();
    }

    @OnClick(R.id.layout_account)
    void onAccountChangeClicked() {
        presenter.changeAccount();
    }

    @OnClick(R.id.layout_expense_category)
    void onCategoryChangeClicked() {
        presenter.changeCategory();
    }

    @OnClick(R.id.button_done)
    void onDoneClicked() {
        presenter.submit();
        getActivity().finish();
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
        textAccountAmount.setText(account.getAmount().toDisplayableString());
    }

    @Override
    public void displayCategory(Category category) {
        textExpenseCategory.setText(category.getName());
    }

    @Override
    public void displayInitialAmount(MoneyAmount initialAmount) {
        textAmount.setText(initialAmount.toDisplayableString());
    }

    @Override
    public void finish() {
        getActivity().finish();
    }
}
