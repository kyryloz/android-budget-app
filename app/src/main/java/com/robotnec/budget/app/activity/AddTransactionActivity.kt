package com.robotnec.budget.app.activity

import android.os.Bundle
import com.robotnec.budget.R
import com.robotnec.budget.app.fragments.AddTransactionFragment
import com.robotnec.budget.app.fragments.CalculatorFragment
import kotlinx.android.synthetic.main.fragment_add_transaction.toolbar
import org.joda.money.Money

class AddTransactionActivity : BaseActivity(), CalculatorFragment.Listener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)

        val supportActionBar = supportActionBar

        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true)
            supportActionBar.setDisplayShowHomeEnabled(true)
        }

        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun layoutId(): Int {
        return R.layout.activity_add_transaction
    }

    override fun onMoneyEdited(money: Money) {
        val addTransactionFragment =
                supportFragmentManager.findFragmentById(R.id.fragment_add_transaction)
                        as? AddTransactionFragment
        addTransactionFragment?.onMoneyEdited(money)
    }
}
