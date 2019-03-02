package com.robotnec.budget.refactored.screens

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.robotnec.budget.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override val layoutId = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navController = navigationHostFragment.findNavController()

        bottomNavigationView.setupWithNavController(navController)
    }
}
