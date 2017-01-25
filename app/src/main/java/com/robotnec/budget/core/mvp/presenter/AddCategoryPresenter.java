package com.robotnec.budget.core.mvp.presenter;

import javax.inject.Inject;

import com.robotnec.budget.core.dao.CategoryDao;
import com.robotnec.budget.core.di.ApplicationComponent;
import com.robotnec.budget.core.domain.Category;
import com.robotnec.budget.core.mvp.view.AddCategoryView;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AddCategoryPresenter extends Presenter<AddCategoryView> {

    @Inject
    CategoryDao categoryDao;

    private final Category category;
    private final boolean editMode;

    public AddCategoryPresenter(AddCategoryView view, Category category) {
        super(view);
        this.category = category;
        editMode = this.category != null;
    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    @Override
    public void onViewReady() {
        if (editMode) {
            mView.initEditMode(category);
        }
    }

    public void addOrEditCategory(String categoryName) {
        if (editMode) {
            category.setName(categoryName);
            categoryDao.updateCategory(category);
        } else {
            Category category = new Category();
            category.setName(categoryName);
            categoryDao.addCategory(category);
        }
    }

    public void deleteCategory() {
        categoryDao.removeCategory(category.getId());
        mView.closeView();
    }
}