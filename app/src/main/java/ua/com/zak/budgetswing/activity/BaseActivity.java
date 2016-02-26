package ua.com.zak.budgetswing.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import ua.com.zak.budgetswing.navigator.InternalNavigator;
import ua.com.zak.budgetswing.navigator.Navigator;

/**
 * @author zak <zak@swingpulse.com>
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Navigator mInternalNavigator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mInternalNavigator = new InternalNavigator(this);
    }

    protected abstract int getLayoutId();
}
