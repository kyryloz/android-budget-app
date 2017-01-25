package com.robotnec.budget.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.robotnec.budget.R;
import com.robotnec.budget.core.domain.Account;
import com.robotnec.budget.fragments.AddAccountFragment;
import com.robotnec.budget.util.Keys;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AddAccountActivity extends com.robotnec.budget.activity.BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            Bundle args = getIntent().getExtras();
            Account account = (Account) args.getSerializable(Keys.ACCOUNT);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_fragment, AddAccountFragment.newInstance(account))
                    .commit();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_account;
    }
}
