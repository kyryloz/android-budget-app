package com.robotnec.budget.app.fragments

import android.os.Bundle
import android.view.View
import com.robotnec.budget.R
import com.robotnec.budget.app.util.Keys
import com.robotnec.budget.core.domain.Category
import com.robotnec.budget.core.mvp.presenter.AddCategoryPresenter
import com.robotnec.budget.core.mvp.view.AddCategoryView
import kotlinx.android.synthetic.main.fragment_add_category.*

/**
 * @author zak <zak></zak>@swingpulse.com>
 */
class AddCategoryFragment : BasePresenterFragment<AddCategoryPresenter>(), AddCategoryView {

    override fun getLayoutId(): Int {
        return R.layout.fragment_add_category
    }

    override fun createPresenter(): AddCategoryPresenter {
        val category = arguments.getSerializable(Keys.CATEGORY) as Category
        return AddCategoryPresenter(this, category)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbarBack(toolbar)

        buttonDelete.setOnClickListener { presenter.deleteCategory() }
        buttonDone.setOnClickListener {
            presenter.addOrEditCategory(editCategoryName.text.toString())
            activity.finish()
        }
    }

    override fun initEditMode(category: Category) {
        editCategoryName.setText(category.name)
        editCategoryName.setSelection(editCategoryName.text.length)
        buttonDelete.visibility = View.VISIBLE
    }

    override fun closeView() {
        activity.finish()
    }

    companion object {

        fun newInstance(category: Category): AddCategoryFragment {
            val fragment = AddCategoryFragment()
            val args = Bundle()
            args.putSerializable(Keys.CATEGORY, category)
            fragment.arguments = args
            return fragment
        }
    }
}
