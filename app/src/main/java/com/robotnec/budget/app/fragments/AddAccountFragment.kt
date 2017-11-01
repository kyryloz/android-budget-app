package com.robotnec.budget.app.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import com.robotnec.budget.R
import com.robotnec.budget.core.domain.Account
import com.robotnec.budget.core.mvp.presenter.AddAccountPresenter
import com.robotnec.budget.core.mvp.view.AddAccountView
import kotlinx.android.synthetic.main.fragment_add_account.buttonDelete
import kotlinx.android.synthetic.main.fragment_add_account.buttonDone
import kotlinx.android.synthetic.main.fragment_add_account.editAccountInitialAmount
import kotlinx.android.synthetic.main.fragment_add_account.editAccountName
import kotlinx.android.synthetic.main.fragment_add_account.spinnerCurrency
import kotlinx.android.synthetic.main.fragment_add_account.toolbar
import org.jetbrains.anko.support.v4.withArguments
import org.joda.money.CurrencyUnit

/**
 * @author zak zak@swingpulse.com>
 */
class AddAccountFragment : BasePresenterFragment<AddAccountPresenter>(), AddAccountView {

    companion object {
        const val ARG_ACCOUNT = "account"

        fun newInstance(account: Account?): Fragment {
            if (account != null) {
                return AddAccountFragment().withArguments(ARG_ACCOUNT to account)
            } else {
                return AddAccountFragment().withArguments()
            }
        }
    }

    override val layoutId: Int
        get() = R.layout.fragment_add_account

    override fun createPresenter(): AddAccountPresenter {
        val account = arguments[ARG_ACCOUNT] as? Account
        return AddAccountPresenter(this, account)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbarBack(toolbar)
        val adapter = ArrayAdapter(context,
                android.R.layout.simple_spinner_dropdown_item,
                CurrencyUnit.registeredCurrencies())
        spinnerCurrency.adapter = adapter

        buttonDelete.setOnClickListener { presenter.deleteAccount() }
        buttonDone.setOnClickListener {
            val accountName = editAccountName.text.toString()
            val accountInitialAmount = editAccountInitialAmount.text.toString()
            val accountCurrency = spinnerCurrency.selectedItem.toString()
            presenter.addOrEditAccount(accountName, accountInitialAmount, accountCurrency)
            activity.finish()
        }
    }

    override fun initEditMode(account: Account) {
        editAccountName.setText(account.name)
        editAccountName.setSelection(editAccountName.length())

        editAccountInitialAmount.setText(account.amount.toString())
        editAccountInitialAmount.setSelection(editAccountInitialAmount.length())

        buttonDelete.visibility = View.VISIBLE

        // TODO set currency spinner position
    }

    override fun closeView() {
        activity.finish()
    }
}
