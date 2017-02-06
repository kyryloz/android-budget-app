package com.robotnec.budget.app.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;

import com.robotnec.budget.core.domain.Entity;

import java.util.ArrayList;

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
                (dialog, which) -> dialog.dismiss());

        builderSingle.setAdapter(
                arrayAdapter,
                (dialog, which) -> PickerDialog.this.onClick(items.get(which)));
        return builderSingle.create();
    }

    protected abstract void bindDialog(AlertDialog.Builder builder);

    protected abstract int getLayoutId();

    protected abstract void onClick(T value);
}
