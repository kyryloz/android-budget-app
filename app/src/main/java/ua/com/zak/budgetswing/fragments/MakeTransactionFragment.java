package ua.com.zak.budgetswing.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
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
import ua.com.zak.budgetswing.dialogs.AccountPickerDialogFragment;
import ua.com.zak.budgetswing.dialogs.DatePickerDialogFragment;
import ua.com.zak.budgetswing.model.dao.AccountDao;
import ua.com.zak.budgetswing.model.domen.Account;

/**
 * @author zak <zak@swingpulse.com>
 */
public class MakeTransactionFragment extends BaseFragment
        implements DatePickerDialogFragment.Listener, AccountPickerDialogFragment.Listener {

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
    private AccountDao mAccountDao;
    private java.text.DateFormat mDateFormat;

    private Calendar mNowDate;
    private Calendar mResultDate;
    private boolean mYesterdayChose;
    private Account mCurrentAccount;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_make_transaction;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAccountDao = mApplicationModel.getDaoFactory().getAccountDao();
        mNowDate = Calendar.getInstance();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDatePickerButton();
        initFirstAccount();
        initExpenseCategory();
        initSubmitButtons();
    }

    @OnClick(R.id.radio_button_date_picker)
    void onRadioDatePickerClicked() {
        showDateChooserDialog();
    }

    @OnCheckedChanged(R.id.radio_button_yesterday)
    void onRadioYesterdayCheckedChanged(boolean checked) {
        mYesterdayChose = checked;
    }


    @OnClick(R.id.layout_account)
    void onAccountChangeClicked() {
        AccountPickerDialogFragment dialog =
                AccountPickerDialogFragment.newInstance(mAccountDao.getAllAccounts(), this);
        dialog.show(getFragmentManager(), AccountPickerDialogFragment.TAG);
    }

    @Override
    public void onDatePicked(Calendar calendar) {
        mResultDate = calendar;
        mRadioDatePicker.setText(mDateFormat.format(calendar.getTime()));
    }

    @Override
    public void onAccountPicked(Account account) {
        bindAccount(account);
    }

    private void showDateChooserDialog() {
        DatePickerDialogFragment datePicker = DatePickerDialogFragment.newInstance(mResultDate, this);
        datePicker.show(getFragmentManager(), DatePickerDialogFragment.TAG);
    }

    private void initDatePickerButton() {
        mDateFormat = DateFormat.getDateFormat(getContext());
        String format = mDateFormat.format(Calendar.getInstance().getTime());
        mRadioDatePicker.setText(format);
    }

    private void initFirstAccount() {
        List<Account> allAccounts = mAccountDao.getAllAccounts();
        Account first = allAccounts.get(0);
        bindAccount(first);
    }

    private void bindAccount(Account account) {
        mCurrentAccount = account;
        mTextAccountName.setText(account.getName());
        mTextAccountAmount.setText(getString(
                R.string.accounts_amount_format,
                account.getAmount(),
                account.getCurrencyCode()));
    }

    private void initExpenseCategory() {
        mTextExpenseCategory.setText("Groceries");
    }

    private void initSubmitButtons() {
        mButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mYesterdayChose) {
                    mNowDate.add(Calendar.DAY_OF_YEAR, -1);
                    mResultDate = mNowDate;
                }

                long amount = Long.valueOf(mEditAmount.getText().toString());
                mAccountDao.makeTransaction(mCurrentAccount.getId(), -amount);
                getActivity().finish();
            }
        });
        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }
}
