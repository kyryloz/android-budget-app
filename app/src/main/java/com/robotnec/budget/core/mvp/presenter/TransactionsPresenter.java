package com.robotnec.budget.core.mvp.presenter;

import java.util.List;

import javax.inject.Inject;

import com.robotnec.budget.core.dao.MoneyOperationDao;
import com.robotnec.budget.core.di.ApplicationComponent;
import com.robotnec.budget.core.domain.money.MoneyOperation;
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
    MoneyOperationDao moneyOperationDao;

    public TransactionsPresenter(TransactionsView view) {
        super(view);
    }

    @Override
    public void onViewReady() {
        List<MoneyOperation> moneyOperations = moneyOperationDao.getAll();
        view.displayTransactions(moneyOperations);
    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    public void addNewTransaction(NavigationBundle navigationBundle) {
        navigator.openAddTransactionScreen(navigationBundle);
    }
}
