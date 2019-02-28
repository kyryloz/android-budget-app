package com.robotnec.budget.app.activity

import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.fragment.app.Fragment
import androidx.core.view.GravityCompat
import android.view.Menu
import android.view.MenuItem
import com.robotnec.budget.R
import com.robotnec.budget.app.fragments.AboutFragment
import com.robotnec.budget.app.fragments.AccountsFragment
import com.robotnec.budget.app.fragments.CategoriesFragment
import com.robotnec.budget.app.fragments.HomeFragment
import com.robotnec.budget.app.fragments.TransactionsFragment
import com.robotnec.budget.core.mvp.presenter.MainPresenter
import com.robotnec.budget.core.mvp.view.MainView
import kotlinx.android.synthetic.main.activity_main.drawer
import kotlinx.android.synthetic.main.activity_main.nav_view

class MainActivity : BasePresenterActivity<MainPresenter>(), NavigationView.OnNavigationItemSelectedListener, MainView {

    override val layoutId = R.layout.activity_main

    override fun createPresenter(): MainPresenter {
        return MainPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.layout_fragment_container, HomeFragment.newInstance())
                    .commit()
        }

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragment: Fragment = when (item.itemId) {
            R.id.nav_home -> HomeFragment.newInstance()
            R.id.nav_accounts -> AccountsFragment.newInstance()
            R.id.nav_categories -> CategoriesFragment.newInstance()
            R.id.nav_transactions -> TransactionsFragment.newInstance()
            R.id.nav_settings -> AboutFragment.newInstance()
            else -> throw IllegalArgumentException("Unknown menu: " + item.itemId)
        }

        supportFragmentManager.beginTransaction()
                .replace(R.id.layout_fragment_container, fragment)
                .commit()

        drawer.closeDrawer(GravityCompat.START)

        title = item.title

        item.isChecked = true

        return true
    }
}
