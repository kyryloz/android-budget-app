package com.robotnec.budget.app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import com.robotnec.budget.R
import com.robotnec.budget.app.adapters.CategoriesAdapter
import com.robotnec.budget.app.navigator.AndroidNavigationBundle
import com.robotnec.budget.core.domain.Category
import com.robotnec.budget.core.mvp.presenter.CategoriesPresenter
import com.robotnec.budget.core.mvp.view.CategoriesView
import kotlinx.android.synthetic.main.fragment_categories.fab
import kotlinx.android.synthetic.main.fragment_categories.recyclerCategories
import kotlinx.android.synthetic.main.fragment_categories.toolbar

/**
 * @author zak zak@swingpulse.com>
 */
class CategoriesFragment : BasePresenterFragment<CategoriesPresenter>(), CategoriesView, CategoriesAdapter.CategoryClickListener {

    companion object {
        fun newInstance(): Fragment {
            return CategoriesFragment()
        }
    }

    private lateinit var categoriesAdapter: CategoriesAdapter

    override val layoutId: Int
        get() = R.layout.fragment_categories

    override fun createPresenter(): CategoriesPresenter {
        return CategoriesPresenter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        fab.setOnClickListener {
            presenter.addOrUpdateCategory(AndroidNavigationBundle(activity as AppCompatActivity, null))
        }
    }

    override fun displayCategories(categories: List<Category>) {
        categoriesAdapter.update(categories)
    }

    override fun onCategoryClick(category: Category) {
        //        AndroidNavigationBundle navigationBundle = new AndroidNavigationBundle((AppCompatActivity) getActivity());
        //        navigationBundle.setSerializableExtra(category);
        //        presenter.addOrUpdateCategory(navigationBundle);
        activity?.let {
            presenter.showCategory(it, category)
        }
    }

    private fun initRecyclerView() {
        context?.let {
            categoriesAdapter = CategoriesAdapter(it, this)
            recyclerCategories.layoutManager = LinearLayoutManager(context)
            recyclerCategories.adapter = categoriesAdapter
        }
    }
}
