package com.robotnec.budget.core.mvp.presenter

import android.app.Activity
import com.robotnec.budget.core.di.ApplicationComponent
import com.robotnec.budget.core.domain.Category
import com.robotnec.budget.core.mvp.view.CategoriesView
import com.robotnec.budget.core.navigator.NavigationBundle
import com.robotnec.budget.core.navigator.Navigator
import com.robotnec.budget.core.persistence.dao.CategoryDao
import javax.inject.Inject

/**
 * @author zak zak@swingpulse.com>
 */
class CategoriesPresenter(view: CategoriesView) : Presenter<CategoriesView>(view) {

    @Inject
    internal lateinit var categoryDao: CategoryDao

    @Inject
    internal lateinit var navigator: Navigator

    override fun injectComponent(applicationComponent: ApplicationComponent) {
        applicationComponent.inject(this)
    }

    override fun onViewResume() {
        view.displayCategories(categoryDao.all)
    }

    fun addOrUpdateCategory(navigationBundle: NavigationBundle<*>) {
        navigator.openAddCategoryScreen(navigationBundle)
    }

    fun showCategory(activity: Activity, category: Category) {
        navigator.openCategory(activity, category)
    }
}
