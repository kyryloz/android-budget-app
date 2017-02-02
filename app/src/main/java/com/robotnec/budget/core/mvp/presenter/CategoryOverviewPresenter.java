package com.robotnec.budget.core.mvp.presenter;

import com.robotnec.budget.core.dao.TransactionDao;
import com.robotnec.budget.core.di.ApplicationComponent;
import com.robotnec.budget.core.domain.Category;
import com.robotnec.budget.core.domain.Transaction;
import com.robotnec.budget.core.mvp.view.CategoryOverviewView;

import java.util.List;

import javax.inject.Inject;

/**
 * @author zak <zak@swingpulse.com>
 */
public class CategoryOverviewPresenter extends Presenter<CategoryOverviewView> {

    @Inject
    TransactionDao transactionDao;

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
        List<Transaction> categoryTransactions = transactionDao.getTransactionsForCategory(category.getId());
        view.displayCategoryTransactions(categoryTransactions);
        view.displayCategoryTitle(category.getName());
    }
}
