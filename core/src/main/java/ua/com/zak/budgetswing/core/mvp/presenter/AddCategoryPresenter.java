package ua.com.zak.budgetswing.core.mvp.presenter;

import javax.inject.Inject;

import ua.com.zak.budgetswing.core.dao.CategoryDao;
import ua.com.zak.budgetswing.core.di.ApplicationComponent;
import ua.com.zak.budgetswing.core.domain.Category;
import ua.com.zak.budgetswing.core.mvp.view.AddCategoryView;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AddCategoryPresenter extends Presenter<AddCategoryView> {

    @Inject
    CategoryDao mCategoryDao;

    private final Category mCategory;
    private final boolean mEditMode;

    public AddCategoryPresenter(AddCategoryView view, Category category) {
        super(view);
        mCategory = category;
        mEditMode = mCategory != null;
    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    @Override
    public void onViewReady() {
        if (mEditMode) {
            mView.initEditMode(mCategory);
        }
    }

    public void addOrEditCategory(String categoryName) {
        if (mEditMode) {
            mCategory.setName(categoryName);
            mCategoryDao.updateCategory(mCategory);
        } else {
            Category category = new Category();
            category.setName(categoryName);
            mCategoryDao.addCategory(category);
        }
    }
}
