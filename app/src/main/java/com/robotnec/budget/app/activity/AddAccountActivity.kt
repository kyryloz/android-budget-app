package com.robotnec.budget.app.activity

import android.os.Bundle
import com.robotnec.budget.R
import com.robotnec.budget.app.fragments.AddAccountFragment
import com.robotnec.budget.app.util.Keys
import com.robotnec.budget.app.util.newFragment
import com.robotnec.budget.core.domain.Account

/**
 * @author zak zak@swingpulse.com>
 */
class AddAccountActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            val args = intent.extras
            val account = args.getSerializable(Keys.ACCOUNT) as Account
            supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_fragment, newFragment<AddAccountFragment>(AddAccountFragment.ARG_ACCOUNT to account))
                    .commit()
        }
    }

    override fun layoutId(): Int {
        return R.layout.activity_add_account
    }
}
