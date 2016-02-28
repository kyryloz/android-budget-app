package ua.com.zak.budgetswing.fragments;

import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import ua.com.zak.budgetswing.R;
import ua.com.zak.budgetswing.core.domain.Account;
import ua.com.zak.budgetswing.core.domain.Category;
import ua.com.zak.budgetswing.core.mvp.presenter.AddTransactionPresenter;
import ua.com.zak.budgetswing.core.mvp.view.AddTransactionView;
import ua.com.zak.budgetswing.dialogs.AccountPickerDialogFragment;
import ua.com.zak.budgetswing.dialogs.CategoryPickerDialogFragment;
import ua.com.zak.budgetswing.dialogs.DatePickerDialogFragment;
import ua.com.zak.budgetswing.dialogs.PickerDialog;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AddTransactionFragment extends BasePresenterFragment<AddTransactionPresenter>
        implements AddTransactionView,
        DatePickerDialogFragment.Listener,
        AccountPickerDialogFragment.Listener,
        CategoryPickerDialogFragment.Listener {

    @Bind(R.id.radio_button_date_picker)
    RadioButton mRadioDatePicker;

    @Bind(R.id.layout_account)
    View mLayoutAccount;

    @Bind(R.id.text_account_name)
    TextView mTextAccountName;

    @Bind(R.id.text_account_amount)
    TextView mTextAccountAmount;

    @Bind(R.id.layout_expense_category)
    View mLayoutExpenseCategory;

    @Bind(R.id.text_expense_category)
    TextView mTextExpenseCategory;

    @Bind(R.id.edit_amount)
    EditText mEditAmount;

    @Bind(R.id.button_cancel)
    Button mButtonCancel;

    @Bind(R.id.button_submit)
    Button mButtonSubmit;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add_transaction;
    }

    @Override
    protected AddTransactionPresenter createPresenter() {
        return new AddTransactionPresenter(this);
    }

    @OnClick(R.id.radio_button_date_picker)
    void onRadioDatePickerClicked() {
        mPresenter.changeDate();
    }

    @OnCheckedChanged(R.id.radio_button_yesterday)
    void onRadioYesterdayCheckedChanged(boolean checked) {
        mPresenter.setYesterdayChose(checked);
    }


    @OnClick(R.id.layout_account)
    void onAccountChangeClicked() {
        mPresenter.changeAccount();
    }

    @OnClick(R.id.layout_expense_category)
    void onCategoryChangeClicked() {
        mPresenter.changeCategory();
    }

    @Override
    public void onDatePicked(Calendar calendar) {
        mPresenter.setChosenDate(calendar);
    }

    @Override
    public void onPicked(Account account) {
        mPresenter.pickAccount(account);
    }

    @Override
    public void onPicked(Category category) {
        mPresenter.pickCategory(category);
    }

    @Override
    public void initDatePickerButton(String date) {
        mRadioDatePicker.setText(date);
    }

    @Override
    public void initSubmitButtons() {
        mButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.submit(Long.valueOf(mEditAmount.getText().toString()));
            }
        });
        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
        mRadioDatePicker.setText(date);
    }

    @Override
    public void showDateChooserDialog(Calendar lastDate) {
        DatePickerDialogFragment datePicker = DatePickerDialogFragment.newInstance(lastDate, this);
        datePicker.show(getFragmentManager(), DatePickerDialogFragment.TAG);
    }

    @Override
    public void displayAccount(Account account) {
        mTextAccountName.setText(account.getName());
        mTextAccountAmount.setText(getString(
                R.string.accounts_amount_format,
                account.getAmount(),
                account.getCurrencyCode()));
    }

    @Override
    public void displayCategory(Category category) {
        mTextExpenseCategory.setText(category.getName());
    }

    @Override
    public void finish() {
        getActivity().finish();
    }
}
