package com.robotnec.budget.app.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.robotnec.budget.R;
import com.robotnec.budget.app.fragments.CategoryOverviewFragment;
import com.robotnec.budget.app.util.Keys;
import com.robotnec.budget.core.domain.Category;

/**
 * @author zak <zak@swingpulse.com>
 */
public class CategoryOverviewActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Bundle args = getIntent().getExtras();
            Category category = (Category) args.getSerializable(Keys.CATEGORY);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_fragment, CategoryOverviewFragment.newInstance(category))
                    .commit();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_category_overview;
    }
}
