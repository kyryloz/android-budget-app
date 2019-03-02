package com.robotnec.budget.refactored.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.robotnec.budget.refactored.di.helpers.Injectable
import dagger.android.AndroidInjection

abstract class BaseActivity : AppCompatActivity() {
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        if (javaClass.isAnnotationPresent(Injectable::class.java)) {
            AndroidInjection.inject(this)
        }

        super.onCreate(savedInstanceState)
        setContentView(layoutId)
    }
}