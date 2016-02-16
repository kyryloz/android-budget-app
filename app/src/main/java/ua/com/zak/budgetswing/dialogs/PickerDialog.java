package ua.com.zak.budgetswing.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import ua.com.zak.budgetswing.core.domen.Entity;

/**
 * @author zak <zak@swingpulse.com>
 */
public abstract class PickerDialog<T extends Entity> extends DialogFragment {

    public static final String TAG = PickerDialog.class.getSimpleName();

    protected static final String BUNDLE_ITEMS = "BundleItems";

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final ArrayList<T> items = (ArrayList<T>) getArguments().getSerializable(BUNDLE_ITEMS);

        if (items == null) {
            throw new NullPointerException();
        }

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());

        bindDialog(builderSingle);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                getContext(),
                getLayoutId());

        for (T item : items) {
            arrayAdapter.add(item.getName());
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

                    @SuppressWarnings("unchecked")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PickerDialog.this.onClick(items.get(which));
                    }
                });
        return builderSingle.create();
    }

    protected abstract void bindDialog(AlertDialog.Builder builder);

    protected abstract int getLayoutId();

    protected abstract void onClick(T value);
}
