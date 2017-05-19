package com.robotnec.budget.app.activity

import android.os.Bundle
import com.robotnec.budget.R
import kotlinx.android.synthetic.main.fragment_add_transaction.*

class AddTransactionActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)

        val supportActionBar = supportActionBar

        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true)
            supportActionBar.setDisplayShowHomeEnabled(true)
        }

        toolbar.setNavigationOnClickListener { v -> finish() }
    }

    override fun layoutId(): Int {
        return R.layout.activity_add_transaction
    }
}
