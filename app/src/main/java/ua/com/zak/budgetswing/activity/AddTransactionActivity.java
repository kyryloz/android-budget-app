package ua.com.zak.budgetswing.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import ua.com.zak.budgetswing.R;

public class AddTransactionActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_transaction;
    }
}
