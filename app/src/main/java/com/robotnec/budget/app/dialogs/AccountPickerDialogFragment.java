package com.robotnec.budget.app.dialogs;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

import com.robotnec.budget.R;
import com.robotnec.budget.core.domain.Account;
import com.robotnec.budget.core.domain.Entity;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AccountPickerDialogFragment<T extends Entity> extends PickerDialog<T> {

    public AccountPickerDialogFragment() {
    }

    public static <T extends Fragment & Listener, S extends Entity> DialogFragment newInstance(
            List<S> items,
            T listener) {
        DialogFragment fragment = new AccountPickerDialogFragment<>();
        fragment.setTargetFragment(listener, 0);
        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_ITEMS, new ArrayList<>(items));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void bindDialog(AlertDialog.Builder builder) {
        builder.setTitle(getContext().getString(R.string.make_transaction_select_account));
    }

    @Override
    protected int getLayoutId() {
        return android.R.layout.select_dialog_item;
    }

    @Override
    protected void onClick(T value) {
        Listener listener = (Listener) getTargetFragment();
        listener.onPicked((Account) value);
    }

    public interface Listener {
        void onPicked(Account account);
    }
}
