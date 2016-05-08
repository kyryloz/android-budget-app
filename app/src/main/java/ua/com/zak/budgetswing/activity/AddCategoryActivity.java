package ua.com.zak.budgetswing.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ua.com.zak.budgetswing.R;
import ua.com.zak.budgetswing.core.domain.Category;
import ua.com.zak.budgetswing.fragments.AddCategoryFragment;
import ua.com.zak.budgetswing.util.Keys;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AddCategoryActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            Bundle args = getIntent().getExtras();
            Category category = (Category) args.getSerializable(Keys.CATEGORY);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_fragment, AddCategoryFragment.newInstance(category))
                    .commit();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_category;
    }
}
