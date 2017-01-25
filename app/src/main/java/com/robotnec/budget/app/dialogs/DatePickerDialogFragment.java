package com.robotnec.budget.app.dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.widget.DatePicker;

import java.util.Calendar;

public class DatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    public static final String TAG = DatePickerDialogFragment.class.getSimpleName();
    private static final String BUNDLE_CALENDAR = "BundleCalendar";

    public static <T extends Fragment & Listener> DatePickerDialogFragment newInstance(
            Calendar currentDate, T listener) {
        DatePickerDialogFragment fragment = new DatePickerDialogFragment();
        fragment.setTargetFragment(listener, 0);
        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_CALENDAR, currentDate);
        fragment.setArguments(args);
        return fragment;
    }

    public DatePickerDialogFragment() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = (Calendar) getArguments().getSerializable(BUNDLE_CALENDAR);
        if (calendar == null) {
            calendar = Calendar.getInstance();
        }
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar result = Calendar.getInstance();
        result.set(Calendar.YEAR, year);
        result.set(Calendar.MONTH, month);
        result.set(Calendar.DAY_OF_MONTH, day);

        Listener listener = (Listener) getTargetFragment();
        listener.onDatePicked(result);
    }

    public interface Listener {
        void onDatePicked(Calendar calendar);
    }
}