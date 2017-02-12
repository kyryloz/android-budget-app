package com.robotnec.budget.core.mvp.presenter;

import com.robotnec.budget.core.di.ApplicationComponent;
import com.robotnec.budget.core.domain.Account;
import com.robotnec.budget.core.mvp.view.HomeView;
import com.robotnec.budget.core.navigator.NavigationBundle;
import com.robotnec.budget.core.navigator.Navigator;
import com.robotnec.budget.core.persistence.dao.AccountDao;
import com.robotnec.budget.core.persistence.dao.TransactionDao;
import com.robotnec.budget.core.service.aggregation.AggregationService;
import com.robotnec.budget.core.service.aggregation.impl.TransactionAggregation;

import java.util.List;

import javax.inject.Inject;

/**
 * @author zak <zak@swingpulse.com>
 */
public class HomePresenter extends Presenter<HomeView> {

    @Inject
    AccountDao accountDao;

    @Inject
    TransactionDao transactionDao;

    @Inject
    AggregationService aggregationService;

    @Inject
    Navigator navigator;

    public HomePresenter(HomeView view) {
        super(view);
    }

    @Override
    public void onViewReady() {
        List<Account> accounts = accountDao.getAll();
        TransactionAggregation aggregation =
                aggregationService.aggregate(transactionDao.getAll(),
                        AggregationService.Resolution.DAY);
        view.displayAccountsWithTransactions(accounts, aggregation);
    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    public void addTransaction(NavigationBundle navigationBundle) {
        navigator.openAddTransactionScreen(navigationBundle);
    }
}
