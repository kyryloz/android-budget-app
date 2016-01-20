package ua.com.zak.budgetswing.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import ua.com.zak.budgetswing.R;
import ua.com.zak.budgetswing.model.domen.Account;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AccountPickerDialogFragment extends DialogFragment {

    public static final String TAG = AccountPickerDialogFragment.class.getSimpleName();
    private static final String BUNDLE_ACCOUNTS = "BundleAccounts";

    public static  <T extends Fragment & Listener> AccountPickerDialogFragment newInstance(
            List<Account> accounts, T listener) {
        AccountPickerDialogFragment fragment = new AccountPickerDialogFragment();
        fragment.setTargetFragment(listener, 0);
        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_ACCOUNTS, new ArrayList<>(accounts));
        fragment.setArguments(args);
        return fragment;
    }

    public AccountPickerDialogFragment() { }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final ArrayList<Account> accounts = (ArrayList<Account>) getArguments().getSerializable(BUNDLE_ACCOUNTS);

        if (accounts == null) {
            throw new NullPointerException();
        }

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());
        builderSingle.setTitle(getContext().getString(R.string.make_transaction_select_account));

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.select_dialog_item);
        for (Account account : accounts) {
            arrayAdapter.add(account.getName());
        }

        builderSingle.setNegativeButton(
                android.R.string.cancel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builderSingle.setAdapter(
                arrayAdapter,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Listener listener = (Listener) getTargetFragment();
                        listener.onAccountPicked(accounts.get(which));
                    }
                });
        return builderSingle.create();
    }

    public interface Listener {
        void onAccountPicked(Account account);
    }
}
