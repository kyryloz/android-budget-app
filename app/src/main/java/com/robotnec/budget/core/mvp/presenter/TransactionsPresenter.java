package com.robotnec.budget.core.mvp.presenter;

import com.robotnec.budget.core.service.aggregation.AggregationService;
import com.robotnec.budget.core.service.aggregation.TransactionAggregation;
import com.robotnec.budget.core.persistence.dao.TransactionDao;
import com.robotnec.budget.core.di.ApplicationComponent;
import com.robotnec.budget.core.domain.operation.Transaction;
import com.robotnec.budget.core.mvp.view.TransactionsView;
import com.robotnec.budget.core.navigator.NavigationBundle;
import com.robotnec.budget.core.navigator.Navigator;

import java.util.List;

import javax.inject.Inject;

/**
 * @author zak <zak@swingpulse.com>
 */
public class TransactionsPresenter extends Presenter<TransactionsView> {

    @Inject
    Navigator navigator;

    @Inject
    TransactionDao transactionDao;

    @Inject
    AggregationService aggregator;

    public TransactionsPresenter(TransactionsView view) {
        super(view);
    }

    @Override
    public void onViewReady() {
        List<Transaction> transactions = transactionDao.getAll();
        TransactionAggregation aggregation =
                aggregator.aggregate(transactions, AggregationService.Resolution.DAY);
        view.displayTransactions(aggregation);
    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    public void addNewTransaction(NavigationBundle navigationBundle) {
        navigator.openAddTransactionScreen(navigationBundle);
    }
}
