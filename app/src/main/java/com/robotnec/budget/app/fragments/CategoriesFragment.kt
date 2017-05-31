package com.robotnec.budget.app.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.robotnec.budget.R
import com.robotnec.budget.app.adapters.CategoriesAdapter
import com.robotnec.budget.app.navigator.AndroidNavigationBundle
import com.robotnec.budget.core.domain.Category
import com.robotnec.budget.core.mvp.presenter.CategoriesPresenter
import com.robotnec.budget.core.mvp.view.CategoriesView
import kotlinx.android.synthetic.main.fragment_categories.*

/**
 * @author zak zak@swingpulse.com>
 */
class CategoriesFragment : BasePresenterFragment<CategoriesPresenter>(), CategoriesView, CategoriesAdapter.CategoryClickListener {

    private lateinit var categoriesAdapter: CategoriesAdapter

    override val layoutId: Int
        get() = R.layout.fragment_categories

    override fun createPresenter(): CategoriesPresenter {
        return CategoriesPresenter(this)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbarToggle(toolbar)
        initRecyclerView()
        fab.setOnClickListener {
            presenter.addOrUpdateCategory(AndroidNavigationBundle(activity as AppCompatActivity))
        }
    }

    override fun displayCategories(categories: List<Category>) {
        categoriesAdapter.update(categories)
    }

    override fun onCategoryClick(category: Category) {
        //        AndroidNavigationBundle navigationBundle = new AndroidNavigationBundle((AppCompatActivity) getActivity());
        //        navigationBundle.setSerializableExtra(category);
        //        presenter.addOrUpdateCategory(navigationBundle);
        presenter.showCategory(activity, category)
    }

    private fun initRecyclerView() {
        categoriesAdapter = CategoriesAdapter(context, this)
        recyclerCategories.layoutManager = LinearLayoutManager(context)
        recyclerCategories.adapter = categoriesAdapter
    }

    companion object {

        fun newInstance(): Fragment {
            return CategoriesFragment()
        }
    }
}
