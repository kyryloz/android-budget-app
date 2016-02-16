package ua.com.zak.budgetswing.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import ua.com.zak.budgetswing.ApplicationModelProvider;
import ua.com.zak.budgetswing.BudgetApplication;
import ua.com.zak.budgetswing.core.ApplicationModel;

/**
 * @author zak <zak@swingpulse.com>
 */
public abstract class BaseActivity extends AppCompatActivity implements ApplicationModelProvider {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
    }

    @Override
    public ApplicationModel getApplicationModel() {
        return ((BudgetApplication) getApplication()).getApplicationModel();
    }

    protected void openActivity(Class<? extends AppCompatActivity> activityClass) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    protected abstract int getLayoutId();
}
