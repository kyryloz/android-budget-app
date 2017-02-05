package com.robotnec.budget.core.mvp.presenter;

import java.util.List;

import javax.inject.Inject;

import com.robotnec.budget.core.dao.TransactionDao;
import com.robotnec.budget.core.di.ApplicationComponent;
import com.robotnec.budget.core.domain.Transaction;
import com.robotnec.budget.core.mvp.view.TransactionsView;
import com.robotnec.budget.core.navigator.NavigationBundle;
import com.robotnec.budget.core.navigator.Navigator;

/**
 * @author zak <zak@swingpulse.com>
 */
public class TransactionsPresenter extends Presenter<TransactionsView> {

    @Inject
    Navigator navigator;

    @Inject
    TransactionDao transactionDao;

    public TransactionsPresenter(TransactionsView view) {
        super(view);
    }

    @Override
    public void onViewReady() {
        List<Transaction> transactions = transactionDao.getAll();
        view.displayTransactions(transactions);
    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    public void addNewTransaction(NavigationBundle navigationBundle) {
        navigator.openAddTransactionScreen(navigationBundle);
    }
}
