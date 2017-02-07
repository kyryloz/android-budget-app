package com.robotnec.budget.core.mvp.presenter;

import com.robotnec.budget.core.dao.MoneyOperationDao;
import com.robotnec.budget.core.di.ApplicationComponent;
import com.robotnec.budget.core.domain.Category;
import com.robotnec.budget.core.domain.operation.MoneyOperation;
import com.robotnec.budget.core.mvp.view.CategoryOverviewView;

import java.util.List;

import javax.inject.Inject;

/**
 * @author zak <zak@swingpulse.com>
 */
public class CategoryOverviewPresenter extends Presenter<CategoryOverviewView> {

    @Inject
    MoneyOperationDao moneyOperationDao;

    private final Category category;

    public CategoryOverviewPresenter(CategoryOverviewView view, Category category) {
        super(view);
        this.category = category;
    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    @Override
    public void onViewReady() {
        List<MoneyOperation> categoryTransactions = moneyOperationDao.getTransactionsForCategory(category.getId());
        view.displayCategoryTransactions(categoryTransactions);
        view.displayCategoryTitle(category.getName());
    }
}
