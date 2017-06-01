package com.robotnec.budget.app.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity


/**
 * @author zak zak@swingpulse.com>
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
    }

    protected abstract fun layoutId(): Int
}
