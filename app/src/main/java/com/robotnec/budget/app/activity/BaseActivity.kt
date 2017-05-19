package com.robotnec.budget.app.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import butterknife.ButterKnife

/**
 * @author zak zak@swingpulse.com>
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        ButterKnife.bind(this)
    }

    protected abstract fun layoutId(): Int
}
