package com.robotnec.budget.app.activity

import android.os.Bundle
import com.robotnec.budget.R
import com.robotnec.budget.app.fragments.AddCategoryFragment
import com.robotnec.budget.app.util.Keys
import com.robotnec.budget.core.domain.Category

/**
 * @author zak zak@swingpulse.com>
 */
class AddCategoryActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            val args = intent.extras
            val category = args.getSerializable(Keys.CATEGORY) as Category
            supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_fragment, AddCategoryFragment.newInstance(category))
                    .commit()
        }
    }

    override fun layoutId(): Int {
        return R.layout.activity_add_category
    }
}
