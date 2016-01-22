package ua.com.zak.budgetswing.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
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
import ua.com.zak.budgetswing.dialogs.CategoryPickerDialogFragment;
import ua.com.zak.budgetswing.dialogs.DatePickerDialogFragment;
import ua.com.zak.budgetswing.dialogs.PickerDialog;
import ua.com.zak.budgetswing.model.dao.AccountDao;
import ua.com.zak.budgetswing.model.dao.CategoryDao;
import ua.com.zak.budgetswing.model.domen.Account;
import ua.com.zak.budgetswing.model.domen.Category;

/**
 * @author zak <zak@swingpulse.com>
 */
public class MakeTransactionFragment extends BaseFragment
        implements DatePickerDialogFragment.Listener,
        AccountPickerDialogFragment.Listener, CategoryPickerDialogFragment.Listener {

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
    private CategoryDao mCategoryDao;

    private java.text.DateFormat mDateFormat;

    private Calendar mNowDate;
    private Calendar mResultDate;
    private boolean mYesterdayChose;
    private Account mResultAccount;
    private Category mResultCategory;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_make_transaction;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAccountDao = mApplicationModel.getDaoFactory().getAccountDao();
        mCategoryDao = mApplicationModel.getDaoFactory().getCategoryDao();
        mNowDate = Calendar.getInstance();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDatePickerButton();
        initFirstAccountAndCategory();
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
        DialogFragment dialog =
                AccountPickerDialogFragment.newInstance(
                        mAccountDao.getAllAccounts(),
                        this);
        dialog.show(getFragmentManager(), PickerDialog.TAG);
    }

    @OnClick(R.id.layout_expense_category)
    void onCategoryChangeClicked() {
        DialogFragment dialog =
                CategoryPickerDialogFragment.newInstance(
                        mCategoryDao.getAllCategories(),
                        this);
        dialog.show(getFragmentManager(), PickerDialog.TAG);
    }

    @Override
    public void onDatePicked(Calendar calendar) {
        mResultDate = calendar;
        mRadioDatePicker.setText(mDateFormat.format(calendar.getTime()));
    }

    @Override
    public void onPicked(Account account) {
        bindAccount(account);
    }

    @Override
    public void onPicked(Category category) {
        bindCategory(category);
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

    private void initFirstAccountAndCategory() {
        List<Account> allAccounts = mAccountDao.getAllAccounts();
        Account first = allAccounts.get(0);
        bindAccount(first);

        List<Category> allCategories = mCategoryDao.getAllCategories();
        Category firstCategory = allCategories.get(0);
        bindCategory(firstCategory);
    }

    private void bindAccount(Account account) {
        mResultAccount = account;
        mTextAccountName.setText(account.getName());
        mTextAccountAmount.setText(getString(
                R.string.accounts_amount_format,
                account.getAmount(),
                account.getCurrencyCode()));
    }

    private void bindCategory(Category category) {
        mResultCategory = category;
        mTextExpenseCategory.setText(category.getName());
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
                mAccountDao.makeTransaction(mResultAccount.getId(), -amount);
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
