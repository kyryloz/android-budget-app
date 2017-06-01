package com.robotnec.budget.core.mvp.presenter

import com.robotnec.budget.core.di.ApplicationComponent
import com.robotnec.budget.core.domain.Category
import com.robotnec.budget.core.mvp.view.AddCategoryView
import com.robotnec.budget.core.persistence.dao.CategoryDao

import javax.inject.Inject

/**
 * @author zak zak@swingpulse.com>
 */
class AddCategoryPresenter(view: AddCategoryView, private val category: Category?) : Presenter<AddCategoryView>(view) {

    @Inject
    internal lateinit var categoryDao: CategoryDao
    private val editMode: Boolean = this.category != null

    override fun injectComponent(applicationComponent: ApplicationComponent) {
        applicationComponent.inject(this)
    }

    override fun onViewResume() {
        if (editMode) {
            view.initEditMode(category!!)
        }
    }

    fun addOrEditCategory(categoryName: String) {
        if (editMode) {
            category!!.name = categoryName
            categoryDao.createOrUpdate(category)
        } else {
            val category = Category(-1, categoryName)
            categoryDao.createOrUpdate(category)
        }
    }

    fun deleteCategory() {
        categoryDao.remove(category!!.id)
        view.closeView()
    }
}
