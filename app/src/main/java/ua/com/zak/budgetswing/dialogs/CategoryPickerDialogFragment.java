package ua.com.zak.budgetswing.dialogs;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

import ua.com.zak.budgetswing.R;
import ua.com.zak.budgetswing.core.domain.Category;
import ua.com.zak.budgetswing.core.domain.Entity;

/**
 * @author zak <zak@swingpulse.com>
 */
public class CategoryPickerDialogFragment<T extends Entity> extends PickerDialog<T> {

    public CategoryPickerDialogFragment() {
    }

    public static <T extends Fragment & Listener, S extends Entity> DialogFragment newInstance(
            List<S> items,
            T listener) {
        DialogFragment fragment = new CategoryPickerDialogFragment<>();
        fragment.setTargetFragment(listener, 0);
        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_ITEMS, new ArrayList<>(items));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void bindDialog(AlertDialog.Builder builder) {
        builder.setTitle(getContext().getString(R.string.make_transaction_select_category));
    }

    @Override
    protected int getLayoutId() {
        return android.R.layout.select_dialog_item;
    }

    @Override
    protected void onClick(T value) {
        Listener listener = (Listener) getTargetFragment();
        listener.onPicked((Category) value);
    }

    public interface Listener {
        void onPicked(Category category);
    }
}
