package com.robotnec.budget.app.dialogs

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.widget.ArrayAdapter

import com.robotnec.budget.core.domain.Entity

import java.util.ArrayList

/**
 * @author zak zak@swingpulse.com>
 */
abstract class PickerDialog<in T : Entity> : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val items = arguments.getSerializable(BUNDLE_ITEMS) as ArrayList<T>

        val builderSingle = AlertDialog.Builder(context)

        bindDialog(builderSingle)

        val arrayAdapter = ArrayAdapter<String>(
                context,
                layoutId)

        for (item in items) {
            arrayAdapter.add(item.name)
        }

        builderSingle.setNegativeButton(
                android.R.string.cancel
        ) { dialog, _ -> dialog.dismiss() }

        builderSingle.setAdapter(
                arrayAdapter
        ) { _, which -> this@PickerDialog.onClick(items[which]) }
        return builderSingle.create()
    }

    protected abstract fun bindDialog(builder: AlertDialog.Builder)

    protected abstract val layoutId: Int

    protected abstract fun onClick(value: T)

    companion object {

        val TAG = PickerDialog::class.java.simpleName!!

        val BUNDLE_ITEMS = "BundleItems"
    }
}
