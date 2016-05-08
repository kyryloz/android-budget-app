package ua.com.zak.budgetswing.core.mvp.presenter;

import javax.inject.Inject;

import ua.com.zak.budgetswing.core.dao.CategoryDao;
import ua.com.zak.budgetswing.core.di.ApplicationComponent;
import ua.com.zak.budgetswing.core.mvp.view.CategoriesView;
import ua.com.zak.budgetswing.core.navigator.NavigationBundle;
import ua.com.zak.budgetswing.core.navigator.Navigator;

/**
 * @author zak <zak@swingpulse.com>
 */
public class CategoriesPresenter extends Presenter<CategoriesView> {

    @Inject
    CategoryDao mCategoryDao;

    @Inject
    Navigator mNavigator;

    public CategoriesPresenter(CategoriesView view) {
        super(view);
    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    @Override
    public void onViewReady() {
        mView.displayCategories(mCategoryDao.getAllCategories());
    }

    public void addOrUpdateCategory(NavigationBundle navigationBundle) {
        mNavigator.openAddCategoryScreen(navigationBundle);
    }
}
