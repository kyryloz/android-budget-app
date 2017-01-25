package ua.com.zak.budgetswing.core.mvp.presenter;

import java.util.List;

import javax.inject.Inject;

import ua.com.zak.budgetswing.core.dao.TransactionDao;
import ua.com.zak.budgetswing.core.di.ApplicationComponent;
import ua.com.zak.budgetswing.core.domain.Transaction;
import ua.com.zak.budgetswing.core.mvp.view.TransactionsView;
import ua.com.zak.budgetswing.core.navigator.NavigationBundle;
import ua.com.zak.budgetswing.core.navigator.Navigator;

/**
 * @author zak <zak@swingpulse.com>
 */
public class TransactionsPresenter extends Presenter<TransactionsView> {

    @Inject
    Navigator mNavigator;

    @Inject
    TransactionDao mTransactionDao;

    public TransactionsPresenter(TransactionsView view) {
        super(view);
    }

    @Override
    public void onViewReady() {
        List<Transaction> transactions = mTransactionDao.getAllTransactions();
        mView.displayTransactions(transactions);
    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    public void addNewTransaction(NavigationBundle navigationBundle) {
        mNavigator.openAddTransactionScreen(navigationBundle);
    }
}
