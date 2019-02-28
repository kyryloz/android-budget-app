package com.robotnec.budget.app.dialogs

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import android.widget.DatePicker
import java.util.Calendar

class DatePickerDialogFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = arguments?.getSerializable(BUNDLE_CALENDAR) as Calendar
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(activity, this, year, month, day)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        val result = Calendar.getInstance()
        result.set(Calendar.YEAR, year)
        result.set(Calendar.MONTH, month)
        result.set(Calendar.DAY_OF_MONTH, day)

        val listener = targetFragment as Listener
        listener.onDatePicked(result)
    }

    interface Listener {
        fun onDatePicked(calendar: Calendar)
    }

    companion object {

        val TAG: String = DatePickerDialogFragment::class.java.simpleName
        private const val BUNDLE_CALENDAR = "BundleCalendar"

        fun <T> newInstance(currentDate: Calendar, listener: T)
                : DatePickerDialogFragment where T : Fragment, T : Listener {
            val fragment = DatePickerDialogFragment()
            fragment.setTargetFragment(listener, 0)
            val args = Bundle()
            args.putSerializable(BUNDLE_CALENDAR, currentDate)
            fragment.arguments = args
            return fragment
        }
    }
}