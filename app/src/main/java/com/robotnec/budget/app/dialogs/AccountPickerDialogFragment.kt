package com.robotnec.budget.app.dialogs

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import com.robotnec.budget.R
import com.robotnec.budget.core.domain.Account
import com.robotnec.budget.core.domain.Entity
import java.util.*

/**
 * @author zak zak@swingpulse.com>
 */
class AccountPickerDialogFragment<in T : Entity> : PickerDialog<T>() {

    override fun bindDialog(builder: AlertDialog.Builder) {
        builder.setTitle(context.getString(R.string.make_transaction_select_account))
    }

    override val layoutId: Int
        get() = android.R.layout.select_dialog_item

    override fun onClick(value: T) {
        val listener = targetFragment as Listener
        listener.onPicked(value as Account)
    }

    interface Listener {
        fun onPicked(account: Account)
    }

    companion object {

        fun <T, S : Entity> newInstance(
                items: List<S>,
                listener: T): DialogFragment where T : Fragment, T : Listener {
            val fragment = AccountPickerDialogFragment<Entity>()
            fragment.setTargetFragment(listener, 0)
            val args = Bundle()
            args.putSerializable(PickerDialog.Companion.BUNDLE_ITEMS, ArrayList(items))
            fragment.arguments = args
            return fragment
        }
    }
}
