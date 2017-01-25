package com.robotnec.budget.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.robotnec.budget.R;
import com.robotnec.budget.core.mvp.presenter.MainPresenter;
import com.robotnec.budget.core.mvp.view.MainView;
import com.robotnec.budget.fragments.AboutFragment;
import com.robotnec.budget.fragments.AccountsFragment;
import com.robotnec.budget.fragments.CategoriesFragment;
import com.robotnec.budget.fragments.HomeFragment;
import com.robotnec.budget.fragments.TransactionsFragment;

public class MainActivity extends BasePresenterActivity<MainPresenter>
        implements NavigationView.OnNavigationItemSelectedListener, MainView {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.layout_fragment_container, HomeFragment.newInstance())
                    .commit();
        }

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment;

        int id = item.getItemId();
        switch (id) {
            case R.id.nav_home:
                fragment = HomeFragment.newInstance();
                break;
            case R.id.nav_accounts:
                fragment = AccountsFragment.newInstance();
                break;
            case R.id.nav_categories:
                fragment = CategoriesFragment.newInstance();
                break;
            case R.id.nav_transactions:
                fragment = TransactionsFragment.newInstance();
                break;
            case R.id.nav_about:
                fragment = AboutFragment.newInstance();
                break;
            default:
                throw new IllegalArgumentException("Unknown menu: " + id);
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_fragment_container, fragment)
                .commit();

        drawerLayout.closeDrawer(GravityCompat.START);

        setTitle(item.getTitle());

        item.setChecked(true);

        return true;
    }
}
