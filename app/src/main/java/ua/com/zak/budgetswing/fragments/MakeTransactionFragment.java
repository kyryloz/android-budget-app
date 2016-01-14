package ua.com.zak.budgetswing.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import ua.com.zak.budgetswing.R;
import ua.com.zak.budgetswing.model.dao.AccountDao;
import ua.com.zak.budgetswing.model.domen.Account;

/**
 * @author zak <zak@swingpulse.com>
 */
public class MakeTransactionFragment extends BaseFragment {

    @Bind(R.id.button_date_picker)
    Button mButtonDatePicker;

    @Bind(R.id.button_yesterday)
    Button mButtonYesterday;

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

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_make_transaction;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAccountDao = mApplicationModel.getDaoFactory().getAccountDao();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDatePicker();
        initAccount();
        initExpenseCategory();
        initSubmitButtons();
    }

    private void initDatePicker() {
        final SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtils.MONTH_DAY_FORMAT, Locale.US);
        mButtonDatePicker.setText(dateFormat.format(Calendar.getInstance().getTime()));
        mButtonDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePicker = new DatePickerDialog(getContext(),
                        android.R.style.Theme_Material_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        mButtonDatePicker.setText(dateFormat.format(calendar.getTime()));
                    }
                },
                        Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                datePicker.setCancelable(false);
                datePicker.setTitle("Select the date");
                datePicker.show();
            }
        });
    }

    private void initAccount() {
        List<Account> allAccounts = mAccountDao.getAllAccounts();
        Account first = allAccounts.get(0);
        mTextAccountName.setText(first.getName());
        mTextAccountAmount.setText(getString(
                R.string.accounts_amount_format,
                first.getAmount(),
                first.getCurrencyCode()));
    }

    private void initExpenseCategory() {
        mTextExpenseCategory.setText("Groceries");
    }

    private void initSubmitButtons() {
        mButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long amount = Long.valueOf(mEditAmount.getText().toString());
                mAccountDao.makeTransaction(mAccountDao.getAllAccounts().get(0).getId(), -amount);
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
