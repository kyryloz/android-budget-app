package com.robotnec.budget.core.mvp.presenter;

import android.app.Activity;

import javax.inject.Inject;

import com.robotnec.budget.core.dao.CategoryDao;
import com.robotnec.budget.core.di.ApplicationComponent;
import com.robotnec.budget.core.domain.Category;
import com.robotnec.budget.core.mvp.view.CategoriesView;
import com.robotnec.budget.core.navigator.NavigationBundle;
import com.robotnec.budget.core.navigator.Navigator;

/**
 * @author zak <zak@swingpulse.com>
 */
public class CategoriesPresenter extends Presenter<CategoriesView> {

    @Inject
    CategoryDao categoryDao;

    @Inject
    Navigator navigator;

    public CategoriesPresenter(CategoriesView view) {
        super(view);
    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    @Override
    public void onViewReady() {
        mView.displayCategories(categoryDao.getAllCategories());
    }

    public void addOrUpdateCategory(NavigationBundle navigationBundle) {
        navigator.openAddCategoryScreen(navigationBundle);
    }

    public void showCategory(Activity activity, Category category) {
        navigator.openCategory(activity, category);
    }
}
