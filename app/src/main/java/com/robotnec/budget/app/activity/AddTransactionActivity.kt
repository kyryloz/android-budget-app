package com.robotnec.budget.app.activity

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar

import com.robotnec.budget.R

import butterknife.BindView

class AddTransactionActivity : BaseActivity() {

    @BindView(R.id.toolbar)
    internal var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)

        val supportActionBar = supportActionBar

        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true)
            supportActionBar.setDisplayShowHomeEnabled(true)
        }

        toolbar!!.setNavigationOnClickListener { v -> finish() }
    }

    override fun layoutId(): Int {
        return R.layout.activity_add_transaction
    }
}
