package com.robotnec.budget.app.activity

import android.os.Bundle

import com.robotnec.budget.R
import com.robotnec.budget.app.fragments.CategoryOverviewFragment
import com.robotnec.budget.app.util.Keys
import com.robotnec.budget.core.domain.Category

/**
 * @author zak zak@swingpulse.com>
 */
class CategoryOverviewActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            val args = intent.extras
            val category = args.getSerializable(Keys.CATEGORY) as Category
            supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_fragment, CategoryOverviewFragment.newInstance(category))
                    .commit()
        }
    }

    override fun layoutId(): Int {
        return R.layout.activity_category_overview
    }
}
