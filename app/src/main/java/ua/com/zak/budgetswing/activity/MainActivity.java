package ua.com.zak.budgetswing.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.Bind;
import ua.com.zak.budgetswing.R;
import ua.com.zak.budgetswing.core.mvp.presenter.MainPresenter;
import ua.com.zak.budgetswing.core.mvp.view.MainView;
import ua.com.zak.budgetswing.fragments.AboutFragment;
import ua.com.zak.budgetswing.fragments.AccountsFragment;
import ua.com.zak.budgetswing.fragments.CategoriesFragment;
import ua.com.zak.budgetswing.fragments.HomeFragment;
import ua.com.zak.budgetswing.fragments.TransactionsFragment;

public class MainActivity extends BasePresenterActivity<MainPresenter>
        implements NavigationView.OnNavigationItemSelectedListener, MainView {

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawer;

    @Bind(R.id.nav_view)
    NavigationView mNavigationView;

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

        mNavigationView.setNavigationItemSelectedListener(this);
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

        mDrawer.closeDrawer(GravityCompat.START);

        setTitle(item.getTitle());

        item.setChecked(true);

        return true;
    }
}
