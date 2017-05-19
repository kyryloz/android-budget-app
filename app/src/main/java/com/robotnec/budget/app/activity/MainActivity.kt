package com.robotnec.budget.app.activity

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.view.Menu
import android.view.MenuItem
import com.robotnec.budget.R
import com.robotnec.budget.app.fragments.*
import com.robotnec.budget.core.mvp.presenter.MainPresenter
import com.robotnec.budget.core.mvp.view.MainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BasePresenterActivity<MainPresenter>(), NavigationView.OnNavigationItemSelectedListener, MainView {

    override val layoutId: Int
        get() = R.layout.activity_main

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
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
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
        val fragment: Fragment

        val id = item.itemId
        when (id) {
            R.id.nav_home -> fragment = HomeFragment.newInstance()
            R.id.nav_accounts -> fragment = AccountsFragment.newInstance()
            R.id.nav_categories -> fragment = CategoriesFragment.newInstance()
            R.id.nav_transactions -> fragment = TransactionsFragment.newInstance()
            R.id.nav_settings -> fragment = AboutFragment.newInstance()
            else -> throw IllegalArgumentException("Unknown menu: " + id)
        }

        supportFragmentManager.beginTransaction()
                .replace(R.id.layout_fragment_container, fragment)
                .commit()

        drawer_layout.closeDrawer(GravityCompat.START)

        title = item.title

        item.isChecked = true

        return true
    }
}
