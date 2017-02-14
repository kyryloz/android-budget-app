package com.robotnec.budget.core.mvp.presenter;

import com.robotnec.budget.core.di.ApplicationComponent;
import com.robotnec.budget.core.domain.Category;
import com.robotnec.budget.core.domain.operation.Transaction;
import com.robotnec.budget.core.mvp.view.CategoryOverviewView;
import com.robotnec.budget.core.persistence.dao.TransactionDao;
import com.robotnec.budget.core.service.aggregation.AggregationService;
import com.robotnec.budget.core.service.aggregation.impl.TransactionAggregation;

import java.util.List;

import javax.inject.Inject;

/**
 * @author zak <zak@swingpulse.com>
 */
public class CategoryOverviewPresenter extends Presenter<CategoryOverviewView> {

    @Inject
    TransactionDao transactionDao;

    @Inject
    AggregationService aggregationService;

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
    public void onViewResume() {
        List<Transaction> categoryTransactions = transactionDao.getTransactionsForCategory(category.getId());
        TransactionAggregation aggregation =
                aggregationService.aggregate(categoryTransactions, AggregationService.Resolution.DAY);
        view.displayCategoryTransactions(aggregation);
        view.displayCategoryTitle(category.getName());
    }
}
