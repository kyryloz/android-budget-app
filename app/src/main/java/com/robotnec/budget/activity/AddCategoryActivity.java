package com.robotnec.budget.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.robotnec.budget.R;
import com.robotnec.budget.core.domain.Category;
import com.robotnec.budget.fragments.AddCategoryFragment;
import com.robotnec.budget.util.Keys;

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
