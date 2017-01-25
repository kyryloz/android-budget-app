package ua.com.zak.budgetswing.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ua.com.zak.budgetswing.R;
import ua.com.zak.budgetswing.core.domain.Account;
import ua.com.zak.budgetswing.fragments.AddAccountFragment;
import ua.com.zak.budgetswing.util.Keys;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AddAccountActivity extends BaseActivity {

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
